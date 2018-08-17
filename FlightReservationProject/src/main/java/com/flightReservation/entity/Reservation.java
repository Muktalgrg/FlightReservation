package com.flightReservation.entity;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.flightReservation.entity.serializerDeserializer.CustomDateAndTimeDeserializer;
import com.flightReservation.entity.serializerDeserializer.CustomDateAndTimeSerializer;

@Entity
public class Reservation extends AbstractEntity {
	
	private Boolean checkedIn;
	private int numberOfBags;
	
	@Column(unique = true)
	private String reservationToken;

	@OneToOne
	private Flight flight;

	@OneToOne
	private Passenger passenger;
	
	@CreationTimestamp
	@JsonSerialize(using = CustomDateAndTimeSerializer.class)
	@JsonDeserialize(using = CustomDateAndTimeDeserializer.class)
//	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime reservedDate;

	
	@UpdateTimestamp
//	@Temporal(TemporalType.TIMESTAMP) updated from java.util.Date to LocalDateTime
	@JsonSerialize(using = CustomDateAndTimeSerializer.class)
	@JsonDeserialize(using = CustomDateAndTimeDeserializer.class)
	private LocalDateTime updatedDate;
	
//	@PrePersist
//	public void reservedDate() {
//		this.reservedDate = new Date(System.currentTimeMillis());
//	}
	
	@JsonIgnore
	@ManyToOne
	private User user;

	public Reservation() {
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

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight2) {
		this.flight = flight2;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public LocalDateTime getReservedDate() {
		return reservedDate;
	}

	public void setReservedDate(LocalDateTime reservedDate) {
		this.reservedDate = reservedDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getReservationToken() {
		return reservationToken;
	}

	public void setReservationToken(String reservationToken) {
		this.reservationToken = reservationToken;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Reservation [checkedIn=" + checkedIn + ", numberOfBags=" + numberOfBags + ", reservationToken="
				+ reservationToken + ", flight=" + flight + ", passenger=" + passenger + ", reservedDate="
				+ reservedDate + ", updatedDate=" + updatedDate + "]";
	}

	

	// TODO Many to many -- feature to be added later
	// private User user;

}
