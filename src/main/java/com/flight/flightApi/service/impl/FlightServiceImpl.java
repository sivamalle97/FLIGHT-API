package com.flight.flightApi.service.impl;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.flightApi.Entity.Flight;
import com.flight.flightApi.Exception.DataNotFoundInDbException;
import com.flight.flightApi.dto.FlightDto;
import com.flight.flightApi.enumaration.SortField;
import com.flight.flightApi.enumaration.SortOrder;
import com.flight.flightApi.repository.FlightRepository;
import com.flight.flightApi.service.FlightService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FlightServiceImpl implements FlightService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FlightServiceImpl.class);

	@Autowired
	private FlightRepository flightReposioty;
	List<Flight> flightsList;


	/*
	 * This  method contains four parameter origin,destination,price, duration
	 * Based on origin and destination parameters API will execute.
	 * Here we checking Flights List data suppose data is empty it will throw DataNotFoundInDbException
	 * Based on price, duration parameters we can sorting the Flights data
	 * This price and duration parameters are optional 
	 * Finally we are mapping Flights List data to Flights DTO List data to show end user.
	 */

	@Override
	public List<FlightDto> findAll(String origin, String destination,SortField sortField,SortOrder sortOrder) {

		flightsList = flightReposioty.findByOriginAndDestination(origin, destination);
		List<FlightDto> flightsListDto = new ArrayList<>();
		if(flightsList.isEmpty()) {
			throw new DataNotFoundInDbException("DATA IS NOT FOUND");
		}else {
			if(sortField.equals(SortField.PRICE)) {
				flightsList = sortPrice(sortOrder);
				flightsListDto = flightsList.stream().map(flight->mapToDto(flight)).collect(Collectors.toList());
				return flightsListDto;
			}else if(sortField.equals(SortField.DURATION)) {
				flightsList = sortDuration(sortOrder);
				flightsListDto = flightsList.stream().map(flight->mapToDto(flight)).collect(Collectors.toList());
				return flightsListDto;
			}
			return flightsListDto;
		}
	} 

	public FlightDto mapToDto(Flight flight) { 
		FlightDto dto = new FlightDto();
		dto.setId(flight.getId()); 
		dto.setFlightNumber(flight.getFlightNumber());
		dto.setOrigin(flight.getOrigin());
		dto.setDestination(flight.getDestination());
		dto.setDepartureTime(flight.getDepartureTime());
		dto.setArrivalTime(flight.getArrivalTime()); 
		dto.setPrice(flight.getPrice());
		return dto;
	}

	private List<Flight> sortPrice(SortOrder sortOrder){
		if(sortOrder.equals(SortOrder.DESC)) {
			Collections.sort(flightsList, Comparator.comparing(Flight::getPrice).reversed());
		}else if(sortOrder.equals(SortOrder.ASC)){
			Collections.sort(flightsList, Comparator.comparing(Flight::getPrice));
		}
		return flightsList;
	}
	private List<Flight> sortDuration(SortOrder sortOrder){
		if(sortOrder.equals(SortOrder.DESC)) {
			Collections.sort(flightsList,Comparator.comparing(flight->{
				Duration duration = Duration.between(((Flight) flight).getDepartureTime(), ((Flight) flight).getArrivalTime());
				return !duration.isNegative() && !duration.isZero();}).reversed());
		}else if(sortOrder.equals(SortOrder.ASC)){
			Collections.sort(flightsList, Comparator.comparing(flight->{
				Duration duration = Duration.between(flight.getDepartureTime(), flight.getArrivalTime());
				return !duration.isNegative() && !duration.isZero();}));
		}
		return flightsList;
	}


}








