package com.flight.flightApi.service;

import java.util.List;

import com.flight.flightApi.dto.FlightDto;
import com.flight.flightApi.model.Flight;
import com.flight.flightApi.model.Flight;

public interface FlightService {
	
	List<Flight> findAll(String origin,String destination,Integer price,Long duration);
	

}
