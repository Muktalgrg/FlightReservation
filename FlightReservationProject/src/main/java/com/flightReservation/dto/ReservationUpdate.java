package com.flightReservation.dto;

public class ReservationUpdate {
	private Long id;
	private Boolean checkedIn;
	private int numberOfBags;
	private String reservationToken;

	public ReservationUpdate() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getCheckedIn() {
		return checkedIn;
	}

	public void setCheckedIn(Boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	public int getNumberOfBags() {
		return numberOfBags;
	}

	public void setNumberOfBags(int numberOfBags) {
		this.numberOfBags = numberOfBags;
	}

	public String getReservationToken() {
		return reservationToken;
	}

	public void setReservationToken(String reservationToken) {
		this.reservationToken = reservationToken;
	}
	
	

}
