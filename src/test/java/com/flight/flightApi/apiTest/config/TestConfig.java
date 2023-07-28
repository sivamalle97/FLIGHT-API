package com.flight.flightApi.apiTest.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
	public FlightRepository repo() {
		return null;
	}
	
	@Bean
	public ModelMapper model() {
		return new ModelMapper();
	}
}

