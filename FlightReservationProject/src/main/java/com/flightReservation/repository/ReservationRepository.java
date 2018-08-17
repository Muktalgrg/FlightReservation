package com.flightReservation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightReservation.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	Optional<Reservation> findByReservationToken(String token);

}
