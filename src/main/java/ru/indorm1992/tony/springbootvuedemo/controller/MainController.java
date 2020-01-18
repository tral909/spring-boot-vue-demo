package ru.indorm1992.tony.springbootvuedemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.indorm1992.tony.springbootvuedemo.domain.User;
import ru.indorm1992.tony.springbootvuedemo.repo.MessageRepo;

import java.util.HashMap;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
	private final MessageRepo messageRepo;

	@GetMapping
	public String main(Model model, @AuthenticationPrincipal User user) {
		HashMap<Object, Object> data = new HashMap<>();

		data.put("profile", user);
		data.put("messages", messageRepo.findAll());

		model.addAttribute("frontendData", data);
		return "index";
	}
}
