package com.flightReservation.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightReservation.entity.Flight;
import com.flightReservation.entity.FlightInformation;
import com.flightReservation.exception.FlightInformationNotFoundException;
import com.flightReservation.repository.FlightInformationRepository;
import com.flightReservation.repository.FlightRepository;
import com.flightReservation.service.FlightService;

@Controller
public class FlightController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class);
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private FlightInformationRepository flightInformationRepository;
	
	@Autowired
	private FlightService flightService;
	
	@GetMapping("/admin/addFlights")
	public String addFlight(Model model) {
		LOGGER.info("inside addFlights()");
		// while adding flight by admin make sure no of reserved seats is 0.
		return "flight/addFlights";
	}
	
	
	@PostMapping("/admin/processFlight")
	@ResponseBody
	public String processFlight(@RequestBody String flight) {
		LOGGER.info("inside processFlight()");
		
		Flight saveFlight = mapFlightStrToObj(flight);
		
		flightRepository.save(saveFlight);
		
		return "{\"msg\":\"Flight successfully added\"} ";
	}


	/** 
	 * use DTO here
	 */
	private Flight mapFlightStrToObj(String flight) {
		JsonNode root = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			root = mapper.readTree(flight);
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		Long flightInformationId = root.path("flightCodeId").asLong();
		String departureCity = root.path("departureCity").asText();
		String arrivalCity = root.path("arrivalCity").asText();
		String operatingAirlines = root.path("operatingAirlines").asText();
		String date = root.path("dateOfDeparture").asText();
		LocalDateTime dateOfDeparture = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		
		Optional<FlightInformation> flightInformation = flightInformationRepository.findById(flightInformationId);
		if(!flightInformation.isPresent()) {
			throw new FlightInformationNotFoundException("Flight information not available");
		}
		
		Flight saveFlight = new Flight();
		saveFlight.setFlightInformation(flightInformation.get());
		saveFlight.setDepartureCity(departureCity);
		saveFlight.setArrivalCity(arrivalCity);
		saveFlight.setOperatingAirlines(operatingAirlines);
		saveFlight.setDateOfDeparture(dateOfDeparture);
		return saveFlight;
	}
	
	
	@GetMapping("/form")
	public String showYoutubeForm() {
		return "flight/addFlights2";
	}

}
