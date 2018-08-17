package com.flightReservation.entity.serializerDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomDateAndTimeSerializer extends JsonSerializer<LocalDateTime> {

	/*
	 * @Override public void serialize(LocalDateTime value, JsonGenerator gen,
	 * SerializerProvider provider) throws IOException { //
	 * gen.writeString(formatter.format(value));
	 * gen.writeString(value.format(formatter));
	 * 
	 * }
	 */
	/*
	 * @Override public LocalDateTime deserialize(JsonParser p,
	 * DeserializationContext ctxt) throws IOException, JsonProcessingException {
	 * DateTimeFormatter formatter =
	 * DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); return
	 * LocalDateTime.parse(p.readValueAs(String.class), formatter); }
	 */

	@Override
	public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		gen.writeString(value.format(formatter));

	}

}
