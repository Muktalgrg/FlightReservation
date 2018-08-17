package com.flightReservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightReservation.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
