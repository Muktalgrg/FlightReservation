package com.flightReservation.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.flightReservation.entity.Reservation;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class PDFGenerator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PDFGenerator.class);


	public void generateItinerary(Reservation reservation, String filePath) {
		LOGGER.info("inside generateItinerary() ");
		Document document = new Document();

		try {
			PdfWriter.getInstance(document, new FileOutputStream(filePath));

			document.open();
			document.add(generateTable(reservation));
			document.close();

		} catch (FileNotFoundException e) {
			LOGGER.error("Exception in generateItinerary "+e);
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private PdfPTable generateTable(Reservation reservation) {
		LOGGER.info("--- creating pdf file ---");
		PdfPTable table = new PdfPTable(2);
		PdfPCell cell;

		cell = new PdfPCell(new Phrase("Flight Itinerary"));
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Flight Details"));
		cell.setColspan(2);
		table.addCell(cell);

		
		table.addCell("Token");
		table.addCell(reservation.getReservationToken());
		
		table.addCell("Price");
		table.addCell(Integer.toString((reservation.getFlight().getFlightInformation().getPrice())));
		
		table.addCell("Airlines");
		table.addCell(reservation.getFlight().getOperatingAirlines());

		table.addCell("Departure City ");
		table.addCell(reservation.getFlight().getDepartureCity());

		table.addCell("Arrival City");
		table.addCell(reservation.getFlight().getArrivalCity());

		table.addCell("Departure date ");
		table.addCell(reservation.getFlight().getDateOfDeparture().toString());

//---------------------------------------------------------------------
		
		cell = new PdfPCell(new Phrase("Passenger Details"));
		cell.setColspan(2);
		table.addCell(cell);

		table.addCell("First name");
		table.addCell(reservation.getPassenger().getFirstName());

		table.addCell("Last name");
		table.addCell(reservation.getPassenger().getLastName());

		table.addCell("Email");
		table.addCell(reservation.getPassenger().getEmail());

		table.addCell("Phone");
		table.addCell(reservation.getPassenger().getPhoneNumber());

		return table;
	}



}
