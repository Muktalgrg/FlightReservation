package com.flightReservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FlightInformationNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 4925407844858436117L;

	public FlightInformationNotFoundException(String msg) {
		super(msg);
	}
}
