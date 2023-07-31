package com.flight.flightApi.apiTest.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.flight.flightApi.controller.FlightRestController;
import com.flight.flightApi.enumaration.SortField;
import com.flight.flightApi.enumaration.SortOrder;
import com.flight.flightApi.repository.FlightRepository;
import com.flight.flightApi.service.FlightService;
import com.flight.flightApi.service.impl.FlightServiceImpl;

@Configuration
public class TestConfig {
	
	@Bean
	public FlightService service() {
		return new FlightServiceImpl();
	}

	
	
	@Bean
	public ModelMapper model() {
		return new ModelMapper();
	}
	
	@Bean
	public SortField sortField() {
		return  null;
	}
	
	@Bean
	public SortOrder sortOrder() {
		return null;
	}
	
	
}

