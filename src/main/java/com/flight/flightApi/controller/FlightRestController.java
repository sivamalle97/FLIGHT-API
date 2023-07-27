package com.flight.flightApi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flight.flightApi.Advice.FiledNotFoundException;
import com.flight.flightApi.dto.FlightDto;
import com.flight.flightApi.model.Flight;
import com.flight.flightApi.service.FlightService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/flight/api")
public class FlightRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FlightRestController.class);
	@Autowired
	private FlightService flightService;
	@Autowired
	private ModelMapper modelMapper;


	@GetMapping("/all/{origin}/{destination}")
	public ResponseEntity<List<FlightDto>> getAllFlights(@PathVariable String origin,
			@PathVariable String destination,
			@RequestParam(value="price", required=false) Integer price,
			@RequestParam(value="duration",required=false) Long duration)
	{
		if(origin == null && destination == null) {
			throw new FiledNotFoundException("Both Origin and Destionation should be enter");
		}
			List<Flight> list = flightService.findAll(origin, destination, price, duration);
		List<FlightDto>	listDtoNew = list.stream()
				.map(flight-> modelMapper.map(flight,FlightDto.class))
				.collect(Collectors.toList());
		LOGGER.info("Getting list of flights base on Origin and Destination");
		return ResponseEntity.ok(listDtoNew);
	}
}
