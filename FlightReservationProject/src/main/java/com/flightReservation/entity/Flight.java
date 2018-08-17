package com.flightReservation.entity;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.flightReservation.entity.serializerDeserializer.CustomDateAndTimeDeserializer;

@Entity
public class Flight extends AbstractEntity {

	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private FlightInformation flightInformation;

	private String operatingAirlines;
	private String departureCity;
	private String arrivalCity;
	
	private int noOfBookings;

	
//	some customization is done in setter
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonDeserialize(using = CustomDateAndTimeDeserializer.class)
	private LocalDateTime dateOfDeparture;

	public Flight() {
		super();
	}

	public int getNoOfBookings() {
		return noOfBookings;
	}

	public void setNoOfBookings(int noOfBookings) {
		this.noOfBookings = noOfBookings;
	}

	public FlightInformation getFlightInformation() {
		return flightInformation;
	}

	public void setFlightInformation(FlightInformation flightInformation) {
		this.flightInformation = flightInformation;
	}

	public String getOperatingAirlines() {
		return operatingAirlines;
	}

	public void setOperatingAirlines(String operatingAirlines) {
		this.operatingAirlines = operatingAirlines;
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

/*	public LocalDateTime getDateOfDeparture() {
		System.out.println("here is the getter called");
		return dateOfDeparture;
	}*/
	
	public String getDateOfDeparture() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String date = formatter.format(dateOfDeparture);
		return date;
	}

	public void setDateOfDeparture(LocalDateTime dateOfDeparture) {
		this.dateOfDeparture = dateOfDeparture;
	}

	@Override
	public String toString() {
		return "Flight [flightInformation=" + flightInformation + ", operatingAirlines=" + operatingAirlines
				+ ", departureCity=" + departureCity + ", arrivalCity=" + arrivalCity + ", noOfBookings=" + noOfBookings
				+ ", dateOfDeparture=" + dateOfDeparture + "]";
	}
	
//	@JsonAnySetter
//	public void setDateOfDeparture(String dateOfDeparture) {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//		LocalDateTime dateTime = LocalDateTime.parse(dateOfDeparture, formatter);
//		this.dateOfDeparture = dateTime;
//	}


	

	



}
