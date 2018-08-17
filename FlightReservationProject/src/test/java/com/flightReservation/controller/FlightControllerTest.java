package com.flightReservation.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightReservation.entity.Flight;
import com.flightReservation.entity.FlightInformation;
import com.flightReservation.exception.FlightInformationNotFoundException;
import com.flightReservation.repository.FlightInformationRepository;
import com.flightReservation.repository.FlightRepository;
import com.flightReservation.service.FlightService;


@RunWith(MockitoJUnitRunner.class)
public class FlightControllerTest {
	@Mock
	private FlightInformationRepository flightInformationRepository;
		
	@Mock
	private FlightRepository flightRepository;
	
	@Mock
	private FlightService flightService;
	
	@InjectMocks
	private FlightController controller;
	
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void showAddFlightPage() throws Exception {
		mockMvc.perform(get("/admin/addFlights"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("flight/addFlights"));
	}
	
	@Test
	public void showProcessFlight() throws Exception{
		
		
	}
	
	
	
	private final static String FLIGHTINPUT = "{\"flightCodeId\":\"3\",\"departureCity\":\"Quinhagak\",\"arrivalCity\":\"Pokhara\",\"operatingAirlines\":\"Yeti\",\"dateOfDeparture\":\"2018-08-18 20:20\"}";
	
//	TODO : REFACTOR later
	@Test
	public void testMapFlightStrToObj() throws Exception {
		JsonNode root = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			root = mapper.readTree(FLIGHTINPUT);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		Long flightInformationId = root.path("flightCodeId").asLong();
		String departureCity = root.path("departureCity").asText();
		String arrivalCity = root.path("arrivalCity").asText();
		String operatingAirlines = root.path("operatingAirlines").asText();
		String date = root.path("dateOfDeparture").asText();
		LocalDateTime dateOfDeparture = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		
		FlightInformation flightInformation = new FlightInformation("3L", 5, 3000);
		Optional<FlightInformation> flightInformationOptional = Optional.of(flightInformation);
	
		when(flightInformationRepository.findById(anyLong())).thenReturn(flightInformationOptional);
		
		Flight saveFlight = new Flight();
		saveFlight.setFlightInformation(flightInformation);
		saveFlight.setDepartureCity(departureCity);
		saveFlight.setArrivalCity(arrivalCity);
		saveFlight.setOperatingAirlines(operatingAirlines);
		saveFlight.setDateOfDeparture(dateOfDeparture);
		
		assertThat(saveFlight.getDepartureCity(), equalTo("Quinhagak"));
		assertThat(saveFlight.getArrivalCity(), equalTo("Pokhara"));
		assertThat(saveFlight.getDateOfDeparture(), equalTo("2018-08-18 20:20"));
	
		
	}



}
