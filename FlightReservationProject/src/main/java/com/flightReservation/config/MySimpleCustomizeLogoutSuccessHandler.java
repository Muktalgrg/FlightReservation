package com.flightReservation.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class MySimpleCustomizeLogoutSuccessHandler implements LogoutSuccessHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MySimpleUrlAuthenticationSuccessHandler.class);
	
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		LOGGER.info("inside logoutSuccess()");
		response.setStatus(HttpServletResponse.SC_OK);
		// redirect to login
		response.sendRedirect("/login");

	}

}
