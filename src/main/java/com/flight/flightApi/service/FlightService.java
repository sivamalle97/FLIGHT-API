package com.flight.flightApi.service;

import java.util.List;

import com.flight.flightApi.dto.FlightDto;
import com.flight.flightApi.enumaration.SortField;
import com.flight.flightApi.enumaration.SortOrder;

public interface FlightService {
	
	List<FlightDto> findAll(String origin,String destination,SortField sortField,SortOrder sortOrder);

	

}
