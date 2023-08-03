package com.flight.flightApi.service.impl;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.flightApi.Entity.Flight;
import com.flight.flightApi.Exception.DataNotFoundInDbException;
import com.flight.flightApi.Exception.FiledNotFoundException;
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
	private  FlightRepository flightRepository;

	List<FlightDto> flightsListDto;


	/*
	 * This  method contains four parameter origin,destination,price, duration
	 * Based on origin and destination parameters API will execute.
	 * Here we checking Flights List data suppose data is empty it will throw DataNotFoundInDbException
	 * Based on price, duration parameters we can sorting the Flights data
	 * This price and duration parameters are optional 
	 * Finally we are mapping Flights List data to Flights DTO List data to show end user.
	 */
	@Override
	public List<FlightDto> flightsList(String origin, String destination) {
		validation(origin, destination);
		List<Flight> list = flightRepository.findByOriginAndDestination(origin, destination);
		if(list.isEmpty()) {
			throw new DataNotFoundInDbException("DATA IS NOT FOUND");
		}
		return list.stream().map(flight->mapToDto(flight)).collect(Collectors.toList());
	}

	@Override
	public List<FlightDto> sortFlights(List<FlightDto> flightDto,SortOrder priceSort,SortOrder durationSort) {
		flightsListDto = flightDto;
		System.out.println(flightsListDto.size()+"   SIZE----------------------------");
		flightsListDto = priceSortMethod(priceSort);
		flightsListDto = durationSortMethod(durationSort);
		return flightsListDto;

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

	private static void validation(String origin, String destination) {
		if(origin == null || origin.isEmpty() || destination == null  || origin.isEmpty() ) {
			throw new FiledNotFoundException("Both Origin and Destionation should be enter");
		}
	}

	public List<FlightDto> priceSortMethod(SortOrder priceSort){
		//if(StringUtils.isNotBlank(priceSort.name()) || StringUtils.isNotEmpty(priceSort.name())) {
		if(priceSort.name()!= null || !priceSort.name().isEmpty()) {
			if(priceSort.equals(SortOrder.DESC)) {
				flightsListDto.sort(Comparator.comparing(FlightDto::getPrice).reversed());
			}else if(priceSort.equals(SortOrder.ASC)){
				flightsListDto.sort(Comparator.comparing(FlightDto::getPrice));
				System.out.println(flightsListDto.size()+"   AFter SIZE----------------------------");
			}
			return flightsListDto;
		}else {
			throw new DataNotFoundInDbException("priceSort field should be enter");
		}
	}
	public List<FlightDto> durationSortMethod(SortOrder durationSort){
		//if(StringUtils.isNotBlank(durationSort.name()) || StringUtils.isNotEmpty(durationSort.name())) {
		if(durationSort.name()!= null || !durationSort.name().isEmpty()) {
			if(durationSort.equals(SortOrder.DESC)) {
				flightsListDto.sort(Comparator.comparing(flight->Duration.between(((FlightDto) flight).getDepartureTime(),(((FlightDto) flight).getArrivalTime()))).reversed());
			}else if(durationSort.equals(SortOrder.ASC)){
				flightsListDto.sort(Comparator.comparing(flight->Duration.between(flight.getDepartureTime(), flight.getArrivalTime())));
			}
			return flightsListDto;
		}else {
			throw new DataNotFoundInDbException("durationSort field should be enter");
		}
	}




}


//Use String Utils at null and empty check
//if priceSort  null and value not equal enum value we need to throw exception, 





