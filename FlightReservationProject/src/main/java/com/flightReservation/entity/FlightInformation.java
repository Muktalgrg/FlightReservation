package com.flightReservation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class FlightInformation extends AbstractEntity {

	@Column(unique = true)
	private String flightCode;

	private int numberOfSeat;

//	private int numberOfReservedSeat;

	private int price;

	public FlightInformation() {
	}
	

	public FlightInformation(String flightCode, int numberOfSeat, int price) {
		super();
		this.flightCode = flightCode;
		this.numberOfSeat = numberOfSeat;
		this.price = price;
	}


	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public int getNumberOfSeat() {
		return numberOfSeat;
	}

	public void setNumberOfSeat(int numberOfSeat) {
		this.numberOfSeat = numberOfSeat;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "FlightInformation [flightCode=" + flightCode + ", numberOfSeat=" + numberOfSeat + ", price=" + price
				+ "]";
	}

	

}
