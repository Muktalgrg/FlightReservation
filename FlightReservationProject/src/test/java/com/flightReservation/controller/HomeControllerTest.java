package com.flightReservation.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import com.flightReservation.repository.UserRepository;
import com.flightReservation.service.security.UserService;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {
	
	@Mock
	UserService userService;
	
	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	private HomeController controller;
	
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void getHomePage() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("home"));
				
	}
	
	@Test
	public void getLoginPage() throws Exception{
		mockMvc.perform(get("/login"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("login/login"));
			
	}
	
	@Test
	public void getRegisterPage() throws Exception{
		mockMvc.perform(get("/register"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("login/register"));
			
	}
	
	@Test
	public void get403Page() throws Exception{
		mockMvc.perform(get("/403"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("login/accessDenied"));
			
	}



}
