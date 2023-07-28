package com.flight.flightApi.service.impl;

import java.time.Duration;
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
import com.flight.flightApi.repository.FlightRepository;
import com.flight.flightApi.service.FlightService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FlightServiceImpl implements FlightService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FlightServiceImpl.class);

	@Autowired
	private FlightRepository flightReposioty;



	/*
	 * This  method contains four parameter origin,destination,price, duration
	 * Based on origin and destination parameters API will execute.
	 * Here we checking Flights List data suppose data is empty it will throw DataNotFoundInDbException
	 * Based on price, duration parameters we can sorting the Flights data
	 * This price and duration parameters are optional 
	 * Finally we are mapping Flights List data to Flights DTO List data to show end user.
	 */
	@Override
	public List<FlightDto> findAll(String origin, String destination,Integer price, Long mduration) {

		List<Flight> flightsList = flightReposioty.findByOriginAndDestination(origin, destination);
		if(flightsList.isEmpty()) {
			throw new DataNotFoundInDbException("DATA IS NOT FOUND");
		}
		if(price == null && mduration == null) {
			LOGGER.info("FETCHING DATA BY USING ORIGIN AND DESTINATION");
			return flightsList.stream().map(flight -> mapToDto(flight)).collect(Collectors.toList());

		}else {
			Comparator<Flight> byPrice = Comparator.comparing(Flight::getPrice);
			Comparator<Flight> byDuration = Comparator.comparing(flight->{
				Duration duration = Duration.between(flight.getDepartureTime(), flight.getArrivalTime());
				return !duration.isNegative() && !duration.isZero();});

			 flightsList.stream().sorted(byPrice).sorted(byDuration).collect(Collectors.toList());
			LOGGER.info("FETCHING DATA AFTER USING FILTER WITH PRICE AND DURATION");
			return flightsList.stream().map(flight -> mapToDto(flight)).collect(Collectors.toList());
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


}
// price with sort
//Directly duration
//flightsList = flightsList.stream()
//.filter(flight->flight.getPrice()<=price)
//.filter(flight->{
//	Duration duration = Duration.between(flight.getDepatureTime(), flight.getArrivalTime());
//	return !duration.isNegative() && !duration.isZero() && duration.toHours()>=minDuration ;})
//.collect(Collectors.toList());

/*

flightsList.stream().filter(flight-> {
		Duration duration = Duration.between(flight.getDepatureTime(), flight.getArrivalTime());
		return !duration.isNegative() && !duration.isZero() &&

				duration.compareTo(minDuration)>=0 && 
				flight.getPrice().compareTo(price) >= 0
				;})*/