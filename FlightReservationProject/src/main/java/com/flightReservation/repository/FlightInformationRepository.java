package com.flightReservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightReservation.entity.FlightInformation;

public interface FlightInformationRepository extends JpaRepository<FlightInformation, Long> {

}
