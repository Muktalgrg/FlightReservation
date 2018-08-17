package com.flightReservation.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.flightReservation.entity.Flight;
import com.flightReservation.repository.FlightInformationRepository;
import com.flightReservation.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {
	
	private FlightRepository flightRepository;
	
	private FlightInformationRepository flightInformationRepository;
	
	public FlightServiceImpl(FlightRepository flightRepository,
			FlightInformationRepository flightInformationRepository) {
		this.flightRepository = flightRepository;
		this.flightInformationRepository = flightInformationRepository;
	}
	
	/**
	 * This method calculates current date and time.
	 * Adds 1 hour to current time and displays all the flights greater or equals to it.
	 */
	@Override
	public List<Flight> getFlights(){
		return flightRepository.getFlightBasedOnDepartureDate(LocalDateTime.now().plusHours(1));
		
	}
//	@Override
//	public List<Flight> getFlights1() {
//		
//		Calendar calendar = new GregorianCalendar();
//		int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
//
//		calendar.add(Calendar.HOUR_OF_DAY, 1);
//		
//		int currentHourAfterAdding1 = calendar.get(Calendar.HOUR_OF_DAY);
//		System.out.println("current hour " +calendar.get(Calendar.HOUR_OF_DAY));
//		
//		Date date1 = new Date(calendar.getTimeInMillis());
//		System.out.println(date1);
//		
//		List<Flight> flights = flightRepository.getFlightBasedOnDepartureDate(new Date(), date1);
//		System.err.println("------------------------------");
//		System.out.println();
//		for(Flight f : flights) {
//			System.out.println(f);
//		}
//		System.err.println("------------------------------");
//		
//		return flights;
//	}
	
//	@Override
//	public List<Flight> getFlights(){
//		List<Flight> flights = flightRepository.findFlight2(new Date());
//		flights.iterator().forEachRemaining(System.out::println);
//		System.out.println(flights.size());
//		System.err.println("------------------------------");
//		System.out.println();
//		
//		List<Flight> output = new ArrayList<>();
//		for(Flight f : flights) {
//			// date that comes from db are either equal or greater than the current date
//			Date date = new Date();
//			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//			String str = formatter.format(date);
//			Date newDate = null;
//			try {
//				newDate = formatter.parse(str);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			int comparision = f.getDateOfDeparture().compareTo(newDate);
//			
//			System.out.println("comparision : "+comparision);
//			// --------------------------------------------------------------------------------
//			// look for today's date but current time is less than
//			if(comparision == 0) { // today's date
//				// if today's date, test for time too with 1 hour later
//				long timeFromDb = f.getEstimatedDepartureTime().getTime();
//				long currentTimeWithHourAdded = System.currentTimeMillis()+ 3600000;
//				
//				System.out.println("from db : "+new Time(timeFromDb)+" -- our time :"+ new Time(currentTimeWithHourAdded));
//				
//				if(currentTimeWithHourAdded <= timeFromDb) {
//					System.out.println("qualified time : "+new Time(timeFromDb));
//					output.add(f);
//				}else {
//					System.out.println("non- qualified time : "+new Time(timeFromDb));
//					// just skip
//				}
//			}else if(comparision == 1){ // future date
//				output.add(f);
//			}
//		}
//		output.iterator().forEachRemaining(System.out::println);
//		
//		System.err.println("------------------------------");
//		System.out.println(output.size());
//		
//		
//		
//		
//		return output;
//		
//	}

}
