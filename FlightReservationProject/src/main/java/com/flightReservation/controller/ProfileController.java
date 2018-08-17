package com.flightReservation.controller;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flightReservation.entity.Reservation;
import com.flightReservation.entity.User;
import com.flightReservation.exception.UserNotFoundException;
import com.flightReservation.repository.UserRepository;

@Controller
public class ProfileController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/profile")
	public String showProfile(Model model, Principal principal) {
		LOGGER.info("Inside showProfile()");
		String username = principal.getName();
		validateUser(username);
		model.addAttribute("user", this.userRepository.findByUserName(username).get());
		return "login/profile";
	}
	
	@PostMapping("/changePassword")
	@ResponseBody
	public String changePassword(@RequestBody User user, Principal principal) {
		LOGGER.info("inside changePassword()");
		Optional<User> userOptional = this.userRepository.findByUserName(user.getUserName());
		if(!userOptional.isPresent() || !principal.getName().equalsIgnoreCase(userOptional.get().getUserName())) {
			return "{\"msg\":\"User not found\"}";
		}

		if(matchFound(user)) {
			System.out.println("1");
			return "{\"msg\":\"Match Found\"} ";
		}else {
			System.out.println("2");
			return "{\"msg\":\"Match not found\"} ";
		}
	}
	
	@PostMapping("/changePasswordConfirm")
	@ResponseBody
	public String changePasswordConfirm(@RequestParam("password")String password, Principal principal) {
		LOGGER.info("inside changePasswordConfirm()");
		User user = getUserByUserName(principal);
		user.setPassword(encoder.encode(password));
		this.userRepository.save(user);
//		return "{\"msg\":\"Password Updated\"} ";
		return "success";
	}
	
	@GetMapping("/reservedFlights")
	@ResponseBody
	public Set<Reservation> reservedFlight(Principal principal){
		LOGGER.info("inside reservedFlight()");
		User user = getUserByUserName(principal);
		System.out.println("reserved seats : "+user.getReservations().size());
		for(Reservation reservation : user.getReservations()) {
			System.out.println(reservation);
		}
		return user.getReservations();
		
	}
	
	private User getUserByUserName(Principal principal) {
		String loggedUser = principal.getName();
		validateUser(loggedUser);
		return this.userRepository.findByUserName(loggedUser).get();
	}

	
	private boolean matchFound(User user) {
		User userFromDb = this.userRepository.findByUserName(user.getUserName()).get();
		System.out.println("password form db : "+userFromDb.getPassword());
		boolean match = BCrypt.checkpw(user.getPassword(), userFromDb.getPassword());
		System.out.println("match : "+match);
		return match;
	}

	private void validateUser(String username) {
		System.out.println("input " +username);
		this.userRepository.findByUserName(username)
			.orElseThrow(()-> new UserNotFoundException("User not found exception"));

	}
	
}
