package com.flightReservation.controller;

import java.security.Principal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flightReservation.dto.ReservationRequest;
import com.flightReservation.entity.Flight;
import com.flightReservation.entity.FlightInformation;
import com.flightReservation.entity.Reservation;
import com.flightReservation.exception.FlightNotFoundException;
import com.flightReservation.repository.FlightRepository;
import com.flightReservation.service.ReservationService;

@Controller
public class ReservationController {
	private final static Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private ReservationService reservationService;

	@GetMapping("/showFlight")
	public String showFlight() {
		LOGGER.info("inside showFlight()");
		return "flight/showFlight";
	}

	@GetMapping("/checkFlightSeat")
	@ResponseBody
	public String checkFlightSeat(@RequestParam("id") Long id) {
		LOGGER.info("inside checkFlightSeat()");

		Optional<Flight> flight = flightRepository.findById(id);

		if (!flight.isPresent()) {
			throw new FlightNotFoundException("Flight with id " + id + " could not be found");
		}

		FlightInformation flightInfo = flight.get().getFlightInformation();
		if (flight.get().getNoOfBookings() < flightInfo.getNumberOfSeat()) {
			System.out.println("1");
			return "success";
		}
		return "All seat has been booked";
	}

	@GetMapping("/reserveFlight")
	public String reserveFlight(@RequestParam("id") long id, Model model) {
		LOGGER.info("inside reserveFlight()");

		Optional<Flight> flight = flightRepository.findById(id);

		if (!flight.isPresent()) {
			throw new FlightNotFoundException("Flight with id " + id + " could not be found");
		}
		model.addAttribute("flight", flight.get());
		return "flight/reserveFlight";
	}

	@PostMapping("/reserveFlight")
	@ResponseBody
	public String saveReservedFlight(@RequestBody ReservationRequest request, Model model, Principal principal) {
		System.out.println("input from user : " + request);
		LOGGER.info("inside saveReservedFlight()");
		String userName = principal.getName();
		Reservation reservation = reservationService.bookFlight(request, userName);
		// model.addAttribute("msg", "Reservation created successfully");
		return "{\"msg\":\"Flight registration completed\"} ";

	}

}
