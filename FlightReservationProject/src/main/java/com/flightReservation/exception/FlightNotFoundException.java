package com.flightReservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FlightNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -596716774354683706L;

	public FlightNotFoundException(String msg) {
		super(msg);
	}

}
