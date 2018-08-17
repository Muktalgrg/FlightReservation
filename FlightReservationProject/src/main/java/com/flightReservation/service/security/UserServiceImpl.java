package com.flightReservation.service.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.flightReservation.entity.Role;
import com.flightReservation.entity.User;
import com.flightReservation.repository.RoleRepository;
import com.flightReservation.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Set<Role> role = new HashSet<>();
		role.add(roleRepository.findById(1L).get());
		
//		if(user.getUserName().equalsIgnoreCase("admin")) {
//			role.add(roleRepository.findById(2L).get());
//		}
		user.setRoles(role);
		userRepository.save(user);
	}

}
