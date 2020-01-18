package ru.indorm1992.tony.springbootvuedemo.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.indorm1992.tony.springbootvuedemo.domain.User;
import ru.indorm1992.tony.springbootvuedemo.repo.UserDetailsRepo;

import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**").authorizeRequests()
				.antMatchers("/", "/login", "/js/**", "/error**").permitAll()
				.anyRequest().authenticated()
				.and().logout().logoutSuccessUrl("/").permitAll()
				.and()
				.csrf().disable();
	}

//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration corsConf = new CorsConfiguration();
//		corsConf.addAllowedMethod("GET");
//		corsConf.addAllowedMethod("POST");
//		corsConf.addAllowedMethod("PUT");
//		corsConf.addAllowedMethod("DELETE");
//		corsConf.addAllowedOrigin("http://localhost:5500");
//		source.registerCorsConfiguration("/**", corsConf);
//		return source;
//	}

	// Настройка CORS для LiveReload в VSC (когда фронт разрабатывается в VSC) - IDEA CE не поддерживает js файлы
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
						.addMapping("/**")
						.allowedMethods("*")
						.allowedOrigins("http://localhost:5500");
			}
		};
	}

	@Bean
	public PrincipalExtractor principalExtractor(UserDetailsRepo userDetailsRepo) {
		return map -> {
			String id = (String) map.get("sub");
			User user = userDetailsRepo.findById(id).orElseGet(() -> {
				User newUser = new User();

				newUser.setId(id);
				newUser.setName((String) map.get("name"));
				newUser.setEmail((String) map.get("email"));
				newUser.setGender((String) map.get("gender"));
				newUser.setGender((String) map.get("gender"));
				newUser.setLocale((String) map.get("locale"));
				newUser.setUserpic((String) map.get("picture"));

				return newUser;
			});

			user.setLastVisit(LocalDateTime.now());
			return userDetailsRepo.save(user);
		};
	}
}
