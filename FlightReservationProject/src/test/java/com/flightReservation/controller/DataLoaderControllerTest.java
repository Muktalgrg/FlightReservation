package com.flightReservation.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.flightReservation.entity.Flight;
import com.flightReservation.entity.FlightInformation;
import com.flightReservation.repository.FlightInformationRepository;
import com.flightReservation.service.FlightService;


@RunWith(MockitoJUnitRunner.class)
public class DataLoaderControllerTest {
	@Mock
	private FlightInformationRepository flightInformationRepository;
	
	@Mock
	@Autowired
	private FlightService flightService;
	
	@InjectMocks
	private DataLoaderController controller;
	
	private MockMvc mockMvc;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
               MediaType.APPLICATION_JSON.getSubtype(),                        
               Charset.forName("utf8")                     
               );


    
	
	@Test
	public void getFlyingFlights() throws Exception {
		FlightInformation flightInformation = new FlightInformation("Y1", 5, 1000);
		Flight flight = new Flight();
		flight.setFlightInformation(flightInformation);
		flight.setArrivalCity("Pokhara");
		flight.setDateOfDeparture(LocalDateTime.now());
		
		when(flightService.getFlights()).thenReturn(Arrays.asList(flight));
		
		mockMvc.perform(get("/flights"))
		   .andExpect(status().isOk())
		
			   .andExpect(content().contentType(APPLICATION_JSON_UTF8))
			   .andExpect(jsonPath("$", hasSize(1)))
			   
			   .andExpect(jsonPath("$[0].arrivalCity", is("Pokhara")))
			   .andExpect(jsonPath("$[0].flightInformation.flightCode", is("Y1")));
			   verify(flightService, times(1)).getFlights();
	}
    
    

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void getAllFlights() throws Exception {
		
		FlightInformation flightInformation = new FlightInformation("Y1", 5, 1000);
		FlightInformation flightInformation1 = new FlightInformation("Y2", 10, 5000);
		
		when(flightInformationRepository.findAll()).thenReturn(Arrays.asList(flightInformation, flightInformation1));
		
		mockMvc.perform(get("/allFlightsInfo"))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(APPLICATION_JSON_UTF8))
			   .andExpect(jsonPath("$", hasSize(2)))
			   
			   .andExpect(jsonPath("$[0].flightCode", is("Y1")))
			   .andExpect(jsonPath("$[0].numberOfSeat", is(5)))
			   .andExpect(jsonPath("$[0].price", is(1000)))
			   
			   .andExpect(jsonPath("$[1].flightCode", is("Y2")))
			   .andExpect(jsonPath("$[1].numberOfSeat", is(10)))
			   .andExpect(jsonPath("$[1].price", is(5000)));
			   
			   verify(flightInformationRepository, times(1)).findAll();
	}


}
