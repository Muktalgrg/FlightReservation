package com.flightReservation.controller.REST.v1;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flightReservation.dto.ReservationUpdate;
import com.flightReservation.entity.Reservation;
import com.flightReservation.exception.ReservationNotFoundException;
import com.flightReservation.repository.ReservationRepository;


/**
 * 
 * TODO : consume it in flightCheckIn project(angular js)
 *
 */


@RestController
public class ReservationRESTController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRESTController.class);
	
	private ReservationRepository reservationRepository;

	public ReservationRESTController(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}
	
	@GetMapping("/reservations")
	public List<Reservation> getAllReservation(){
		return this.reservationRepository.findAll();
	}
	
	
	@GetMapping("/reservations/{reservationToken}")
	public Resource<Reservation> findReservationByToken(@PathVariable("reservationToken") String token) {
		LOGGER.info("inside reservationByToken()");
		validateReservation(token);
		Reservation reservation = reservationRepository.findByReservationToken(token).get();
		
		Resource<Reservation> resource = new Resource<Reservation>(reservation);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllReservation());
		resource.add(linkTo.withRel("selt"));
		
		return resource;
	}
	
	
	@PutMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdate request) {
		LOGGER.info("inside updateReservation for "+request);
		Reservation reservation = reservationRepository.findById(request.getId()).get();
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.getCheckedIn());
		Reservation updatedReservation = reservationRepository.save(reservation);
		LOGGER.info("saving reservation");
		return updatedReservation;
		
		
	}	
	
	private void validateReservation(String token) {
		this.reservationRepository
			.findByReservationToken(token)
			.orElseThrow(() -> new ReservationNotFoundException("Reservation not found for token : "+token));
	}
	

}
