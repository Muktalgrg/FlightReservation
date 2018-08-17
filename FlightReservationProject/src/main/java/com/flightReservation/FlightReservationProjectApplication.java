package com.flightReservation;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import com.flightReservation.entity.FlightInformation;
import com.flightReservation.repository.FlightInformationRepository;
import com.flightReservation.repository.FlightRepository;

@SpringBootApplication
public class FlightReservationProjectApplication implements CommandLineRunner {

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private FlightInformationRepository flightInformationRepository;

	public static void main(String[] args) {
		SpringApplication.run(FlightReservationProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		insert data using sql or by uncommenting insertFlights()
//		insertFlights();
	}

	@Transactional
	private void insertFlights() {
		FlightInformation flightInfo1 = new FlightInformation("Y1", 10, 1000);
		FlightInformation flightInfo2 = new FlightInformation("Y2", 5, 2000);
		FlightInformation flightInfo3 = new FlightInformation("Y3", 4, 3000);
		FlightInformation flightInfo4 = new FlightInformation("Y4", 7, 1000);
		FlightInformation flightInfo5 = new FlightInformation("Y5", 8, 1000);
		
		flightInformationRepository.save(flightInfo1);
		flightInformationRepository.save(flightInfo2);	
		flightInformationRepository.save(flightInfo3);
		flightInformationRepository.save(flightInfo4);
		flightInformationRepository.save(flightInfo5);

	}

}
