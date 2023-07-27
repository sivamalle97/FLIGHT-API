package com.flight.flightApi.dto;

import java.time.LocalTime;
import java.util.List;

import lombok.Data;


@Data
public class FlightDto {
	
	private Integer id;
	
	private String flightNumber;
	
	private String origin;
	
	private String destination;
	
	private LocalTime depatureTime;
	
	private LocalTime arrivalTime;
	
	private Integer price;

	
}
