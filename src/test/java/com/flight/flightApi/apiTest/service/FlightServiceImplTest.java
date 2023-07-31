package com.flight.flightApi.apiTest.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.flight.flightApi.Entity.Flight;
import com.flight.flightApi.dto.FlightDto;
import com.flight.flightApi.enumaration.SortField;
import com.flight.flightApi.enumaration.SortOrder;
import com.flight.flightApi.repository.FlightRepository;
import com.flight.flightApi.service.impl.FlightServiceImpl;

@ExtendWith(MockitoExtension.class)
public class FlightServiceImplTest {

	@Mock
	private FlightRepository repo;

	@InjectMocks
	private FlightServiceImpl impl;

	@Autowired
	private FlightDto dto;
	
	@Autowired
	SortField sortField;
	
	@Autowired
	SortOrder sortOrder;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}


	@Test
	public void testFindFlightsWithAsscendingPrice() { 
		Flight flight1 = new Flight("A101","AMS",LocalTime.of(11,00),LocalTime.of(17,00),"DEL",850.00);
		Flight flight2 = new Flight("B101","AMS",LocalTime.of(12,00),LocalTime.of(19,30),"DEL",750.00); 
		Flight flight3 = new Flight("C101","AMS",LocalTime.of(13,00),LocalTime.of(18, 30),"DEL",800.00);
			List<Flight> flightList = new ArrayList<>();
			flightList.add(flight1);
			flightList.add(flight2);
			flightList.add(flight3);
			lenient().when(repo.findByOriginAndDestination(anyString(), anyString())).thenReturn(flightList);
			
			
			List<FlightDto> existingFlight =impl.findAll("AMS","DEL", SortField.PRICE,SortOrder.ASC);
			  assertNotNull(existingFlight);
			  assertEquals(3,existingFlight.size());
			  assertEquals(750.00,existingFlight.get(0).getPrice());
			  assertEquals(800.00,existingFlight.get(1).getPrice());
			  assertEquals(850.00,existingFlight.get(2).getPrice());
			 
		}
	@Test
	public void testFindFlightsWithcDescendingDuration() { 
		Flight flight1 = new Flight("A101","AMS",LocalTime.of(11,00),LocalTime.of(17,00),"DEL",850.00);
		Flight flight2 = new Flight("B101","AMS",LocalTime.of(12,00),LocalTime.of(19,30),"DEL",750.00); 
		Flight flight3 = new Flight("C101","AMS",LocalTime.of(13,00),LocalTime.of(18, 30),"DEL",800.00);
			List<Flight> flightList = new ArrayList<>();
			flightList.add(flight1);
			flightList.add(flight2);
			flightList.add(flight3);
			lenient().when(repo.findByOriginAndDestination(anyString(), anyString())).thenReturn(flightList);
			
			
			List<FlightDto> existingFlight =impl.findAll("AMS","DEL", SortField.DURATION,SortOrder.DESC);
			  assertNotNull(existingFlight);
			  assertEquals(3,existingFlight.size());
			  assertEquals(750.00,existingFlight.get(1).getPrice());
			  assertEquals(800.00,existingFlight.get(2).getPrice());
			  assertEquals(850.00,existingFlight.get(0).getPrice());
			 
		}


}
