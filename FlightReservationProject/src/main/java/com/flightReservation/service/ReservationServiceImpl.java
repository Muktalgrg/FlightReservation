package com.flightReservation.service;

import java.security.Principal;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.flightReservation.dto.ReservationRequest;
import com.flightReservation.entity.Flight;
import com.flightReservation.entity.Passenger;
import com.flightReservation.entity.Reservation;
import com.flightReservation.entity.User;
import com.flightReservation.exception.FlightNotFoundException;
import com.flightReservation.repository.FlightRepository;
import com.flightReservation.repository.PassengerRepository;
import com.flightReservation.repository.ReservationRepository;
import com.flightReservation.repository.UserRepository;
import com.flightReservation.utils.EmailUtil;
import com.flightReservation.utils.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {
	private Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	
	@Value("${com.flightreservation.itinerary.dirpath}")
	private String ITINERARY_DIR;

	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private PassengerRepository passengerRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PDFGenerator pdfGenerator;
	
	@Autowired
	private EmailUtil emailUtil;
	
	

	public ReservationServiceImpl() {
	}


	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request, String userName) {
		LOGGER.info("Inside bookFlight()");
		

		Reservation reservation = new Reservation();

		Long flightId = request.getFlightId();
		LOGGER.info("fetching flight for flight id : " + flightId);

		Optional<Flight> flight = flightRepository.findById(flightId);
		if (!flight.isPresent()) {
			LOGGER.info("threw exception !!");
			throw new FlightNotFoundException("flight not Found");
		}
		Flight fetchedFlight = flight.get();
		fetchedFlight.setNoOfBookings(fetchedFlight.getNoOfBookings() + 1);
		
		LOGGER.warn("1");
		System.out.println("middle name from user : "+request.getMiddleName());
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getFirstName());
		passenger.setMiddleName(request.getMiddleName());
		passenger.setLastName(request.getLastName());
		passenger.setPhoneNumber(request.getPhone());
		passenger.setEmail(request.getEmail());
		Passenger savedPassenger = passengerRepository.save(passenger);
		LOGGER.warn("2");
		
		reservation.setFlight(fetchedFlight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		

		Reservation savedReservation = reservationRepository.save(reservation);

		// set token only after generating primary key in the db.
		reservation.setReservationToken(generateUniqueToken(reservation.getId()));
		
		LOGGER.warn("3");
		
//		User user = (User) SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userRepository.findByUserName(userName).get();
		
		user.addReservation(savedReservation);
		reservation.setUser(user);
		
		LOGGER.warn("4");
		String filePath = ITINERARY_DIR + savedReservation.getId() + ".pdf";

		LOGGER.info("generating the itinerary");
		pdfGenerator.generateItinerary(savedReservation, filePath);

		LOGGER.info("sending itinerary");
		emailUtil.sendItinerary(passenger.getEmail(), filePath);

		return savedReservation;
	}


	private String generateUniqueToken(Long id) {
		/**
		 * ASCII value ranges from 65-90 (A-Z)
		 */
		StringBuilder strBuilder = new StringBuilder();
		
		for(int i = 0; i < 3; i++) {
			Random random = new Random();
			int val = random.nextInt(25)+65;
			strBuilder.append((char) val);
		}
		
		strBuilder.append(id.toString());
		
		LOGGER.info("Generated value : "+strBuilder);
		return strBuilder.toString();
	}

}
