package com.flightReservation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightReservation.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUserName(String username);
	Optional<User> findByEmail(String email);
	Optional<User> findByUserNameOrEmail(String username, String email);

}
