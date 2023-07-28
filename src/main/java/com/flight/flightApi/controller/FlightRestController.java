package com.flight.flightApi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flight.flightApi.Exception.FiledNotFoundException;
import com.flight.flightApi.dto.FlightDto;
import com.flight.flightApi.service.FlightService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/flight/api")
public class FlightRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FlightRestController.class);
	@Autowired
	private FlightService flightService;

	/*
	 * Here we calling REST-API GET call. It will fetch the data from database based on given parameters
	 * In this method origin and destination parameters are mandatory. suppose any one of them is null 
	 * It will throw FileNotFoundException Exception
	 */
	@GetMapping("/all/{origin}/{destination}")
	public ResponseEntity<List<FlightDto>> getAllFlights(@PathVariable String origin,
			@PathVariable String destination,
			@RequestParam(value="price", required=false) Integer price,
			@RequestParam(value="duration",required=false) Long duration){
		if(origin == null && destination == null) {
			throw new FiledNotFoundException("Both Origin and Destionation should be enter");
		}
		List<FlightDto> list = flightService.findAll(origin, destination, price, duration);
		LOGGER.info("Getting list of flights base on Origin and Destination");
		return ResponseEntity.ok(list);
	}
}
