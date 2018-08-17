package com.flightReservation.service;

import com.flightReservation.dto.ReservationRequest;
import com.flightReservation.entity.Reservation;

public interface ReservationService {
	Reservation bookFlight(ReservationRequest request, String userName);

}
