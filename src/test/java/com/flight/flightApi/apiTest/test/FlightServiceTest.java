package com.flight.flightApi.apiTest.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.flight.flightApi.Entity.Flight;
import com.flight.flightApi.dto.FlightDto;
import com.flight.flightApi.repository.FlightRepository;
import com.flight.flightApi.service.impl.FlightServiceImpl;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

	@Mock
	private FlightRepository repo;

	@InjectMocks
	private FlightServiceImpl impl;

	@Autowired
	private FlightDto dto;


	@Test
	public void findByOriginAndDestionation_return() { 
		Flight flight1 = new Flight("A101","AMS",LocalTime.of(11, 00),LocalTime.of(17, 00),"DEL",850);
		Flight flight2 = new Flight("B101","AMS",LocalTime.of(12,00),LocalTime.of(19, 30),"BOM",750); 
		Flight flight3 = new Flight("C101","AMS",LocalTime.of(13, 00),LocalTime.of(18, 30),"BLR",800);
			List<Flight> flightList = List.of(flight1,flight2,flight3);
			when(repo.findByOriginAndDestination(anyString(),anyString())).thenReturn(
					flightList);

			List<FlightDto> existingFlight =
					impl.findAll(flight1.getOrigin(),flight1.getDestination(),flight1.getPrice(),
							(Duration.between(flight1.getDepartureTime(),flight1.getArrivalTime())).
							toHours()); 
			assertNotNull(existingFlight);
			assertThat(existingFlight.size()).isNotEqualTo(null); 
		}


}
