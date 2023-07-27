package com.flight.flightApi.service.impl;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.flightApi.Advice.DataNotFoundInDbException;
import com.flight.flightApi.dto.FlightDto;
import com.flight.flightApi.model.Flight;
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

	@Override
	public List<Flight> findAll(String origin, String destination,Integer price,Long minDuration) {

		flightsList = flightReposioty.findByOriginAndDestination(origin, destination);
		if(price == null && minDuration == null) {
			if(	flightsList.isEmpty())
				throw new DataNotFoundInDbException("No flights are available with this inputs");
			LOGGER.info("FETCHING DATA BY USING ORIGIN AND DESTINATION");
			return flightsList;
		}else {
			flightsList = flightsList.stream()
					.filter(flight->flight.getPrice()<=price)
					.filter(flight->{
						Duration duration = Duration.between(flight.getDepatureTime(), flight.getArrivalTime());
						return !duration.isNegative() && !duration.isZero() && duration.toHours()>=minDuration ;})
					.collect(Collectors.toList());
			LOGGER.info("FETXHING DATA AFTER USING FILTER WITH PRICE AND DURATION");
			if(flightsList.isEmpty() ) {
				throw new DataNotFoundInDbException("Data is Not Found After filtering");
			}
		}
		return flightsList;
	}



}
