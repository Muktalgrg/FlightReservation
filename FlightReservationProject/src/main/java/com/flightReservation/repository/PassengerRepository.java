package com.flightReservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightReservation.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
