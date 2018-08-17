package com.flightReservation.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flightReservation.entity.Flight;
import com.flightReservation.entity.FlightInformation;
import com.flightReservation.repository.FlightInformationRepository;
import com.flightReservation.service.FlightService;

@Controller
public class DataLoaderController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataLoaderController.class);

	private FlightInformationRepository flightInformationRepository;
	private FlightService flightService;

	public DataLoaderController(FlightInformationRepository flightInformationRepository, FlightService flightService) {
		this.flightInformationRepository = flightInformationRepository;
		this.flightService = flightService;
	}

	@GetMapping("/allFlightsInfo")
	@ResponseBody
	public List<FlightInformation> getAllFlights() {
		LOGGER.info("invoked getAllflights()");
		return flightInformationRepository.findAll();
	}

	// returns all flights flying 1 hour later than current time
	@GetMapping("/flights")
	@ResponseBody
	public List<Flight> getFlyingFlights(Model model) {
		LOGGER.info("inside addFlights()");
//		flightService.getFlights().forEach(System.out::println);
		return flightService.getFlights();
	}

}
