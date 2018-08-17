package com.flightReservation.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flightReservation.entity.User;
import com.flightReservation.repository.UserRepository;
import com.flightReservation.service.security.UserService;

@Controller
public class HomeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	// private SecurityService securityService;
	private UserService userService;
	// private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserRepository userRepository;

	public HomeController(UserService userService, UserRepository userRepository) {
		this.userService = userService;
		this.userRepository = userRepository;
	}

	@GetMapping({ "/", "/index", "/home" })
	public String showHome() {
		LOGGER.info("inside showHome()");
		return "home";
	}

	@GetMapping("/login")
	public String showLogin() {
		LOGGER.info("inside showLogin()");
		return "login/login";
	}

	@GetMapping("/register")
	public String showRegister() {
		LOGGER.info("inside showRegister()");
		// User user = new User();
		return "login/register";
	}

	@PostMapping("/processRegister")
	@ResponseBody
	public String processRegister(@RequestBody User user, Model model) {
		LOGGER.info("inside processRegister()");
		Optional<User> userFound = userRepository.findByUserNameOrEmail(user.getUserName(), user.getEmail());
		if (userFound.isPresent()) {
			return "{\"msg\" : \"failure\"}";
		}
		userService.save(user);
		return "{\"msg\" : \"success\"}";
	}
	
	@GetMapping("/403")
	public String accessDenied() {
		return "login/accessDenied";
	}
	


}
