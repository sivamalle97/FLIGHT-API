package com.flight.flightApi.service;

import java.util.List;

import com.flight.flightApi.dto.FlightDto;

public interface FlightService {
	
	//List<Flight> findAll(String origin,String destination,Integer price,Long duration);
	List<FlightDto> findAll(String origin,String destination,Integer price,Long duration);
	

}
