package com.flightReservation.service.security;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.flightReservation.controller.HomeController;
import com.flightReservation.entity.User;
import com.flightReservation.exception.UserNotFoundException;
import com.flightReservation.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	private UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUserName(username);
		if (!user.isPresent()) {
			LOGGER.info("user is not present as of db");
			throw new UsernameNotFoundException("Could not find user");
		}
		User existingUser = user.get();
		LOGGER.info("user is found !!"+existingUser);
		return new org.springframework.security.core.userdetails.User(existingUser.getUserName(),
				existingUser.getPassword(), existingUser.getRoles());

	}

}
