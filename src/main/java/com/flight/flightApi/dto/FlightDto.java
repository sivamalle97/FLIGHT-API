package com.flight.flightApi.dto;

import java.time.LocalDateTime;

import lombok.Data;


@Data
public class FlightDto {
	
	private Long id;
	
	private String flightNumber;
	
	private String origin;
	
	private String destination;
	
	private LocalDateTime departureTime;
	
	private LocalDateTime arrivalTime;
	
	private Double price;

	
}
