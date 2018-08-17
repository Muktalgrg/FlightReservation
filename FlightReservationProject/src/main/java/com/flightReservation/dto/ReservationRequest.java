package com.flightReservation.dto;

public class ReservationRequest{
	private Long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String phone;
	public ReservationRequest() {
		super();
	}
	public Long getFlightId() {
		return id;
	}
	public void setFlightId(Long flightId) {
		this.id = flightId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "ReservationRequest [flightId=" + id + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", email=" + email + ", phone=" + phone + "]";
	}
	
	
	

}
