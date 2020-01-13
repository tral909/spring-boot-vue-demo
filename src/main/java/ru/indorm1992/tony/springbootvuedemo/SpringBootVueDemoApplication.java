package ru.indorm1992.tony.springbootvuedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpringBootVueDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootVueDemoApplication.class, args);
    }

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

}
