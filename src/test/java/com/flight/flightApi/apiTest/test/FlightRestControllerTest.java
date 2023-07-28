package com.flight.flightApi.apiTest.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.SSLEngineResult.Status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.flight.flightApi.Entity.Flight;
import com.flight.flightApi.apiTest.config.TestConfig;
import com.flight.flightApi.controller.FlightRestController;
import com.flight.flightApi.dto.FlightDto;
import com.flight.flightApi.repository.FlightRepository;
import com.flight.flightApi.service.FlightService;


@ExtendWith(SpringExtension.class)
@WebMvcTest(FlightRestController.class)
@ContextConfiguration(classes = TestConfig.class)
public class FlightRestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Mock
	private FlightService service;
	
	@Autowired
	private FlightRepository repo;
	
	
	@Autowired
	private ModelMapper model;
	
	@Test
	public void testFindAll() {
		Flight flight1 = new Flight("A101","AMS",LocalTime.of(11, 00),LocalTime.of(17, 00),"DEL",850);
		Flight flight2 = new Flight("B101","AMS",LocalTime.of(12,00),LocalTime.of(19, 30),"BOM",750); 
		Flight flight3 = new Flight("C101","AMS",LocalTime.of(13, 00),LocalTime.of(18, 30),"BLR",800);
			List<Flight> flightList = List.of(flight1,flight2,flight3);
			List<FlightDto> listDto = flightList.stream().map(flight-> model.map(flight, FlightDto.class)).collect(Collectors.toList());
			String origin = "AMS";
			String destination = "DEL";
			Integer price = 800;
			Long duration = 1L;
			Mockito.when(service.findAll(origin, destination, price, duration)).thenReturn(listDto);
			
			assertEquals(listDto.size(),3);
			assertEquals(listDto.get(0).getFlightNumber(),"A101");
	}

	
}
