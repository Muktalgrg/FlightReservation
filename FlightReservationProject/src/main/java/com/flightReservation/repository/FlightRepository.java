package com.flightReservation.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flightReservation.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {


/*	@Query("from Flight where departureCity =:departureCity AND arrivalCity =:arrivalCity AND dateOfDeparture =:dateOfDeparture")
	List<Flight> findFlights(@Param("departureCity") String from, @Param("arrivalCity") String to,
			@Param("dateOfDeparture") Date departureDate);*/

	@Query("from Flight where dateOfDeparture >=:dateOfDeparture")
	List<Flight> getFlightBasedOnDepartureDate(@Param("dateOfDeparture") LocalDateTime departure);
	
/*	@Query("from Flight where dateOfDeparture >=:departureDate")
	List<Flight> findFlight2(@Param("departureDate")Date departureDate);
	
	@Query("from Flight where estimatedDepartureTime <=:departureTime")
	List<Flight> findFlight1(@Param("departureTime") Date time);*/

}
