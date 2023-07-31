package com.flight.flightApi.apiTest.config.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.flight.flightApi.Entity.Flight;
import com.flight.flightApi.apiTest.config.TestConfig;
import com.flight.flightApi.controller.FlightRestController;
import com.flight.flightApi.dto.FlightDto;
import com.flight.flightApi.repository.FlightRepository;
import com.flight.flightApi.service.FlightService;

@EnableWebMvc
@ExtendWith(SpringExtension.class)
@WebMvcTest(FlightRestController.class)
@ContextConfiguration(classes = TestConfig.class)
@Import(FlightRestController.class)
public class FlightRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private FlightService service;
	
	
	
	@InjectMocks
	private FlightRestController controller;
	

	@Before
	public void setup() {
		mockMvc= MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Autowired
	private ModelMapper model;
	
	
	@Test
	public void testFindAll() throws Exception {
		Flight flight1 = new Flight("A101","AMS",LocalTime.of(11,00),LocalTime.of(17,00),"DEL",850.00);
		Flight flight2 = new Flight("B101","AMS",LocalTime.of(12,00),LocalTime.of(19,30),"DEL",750.00); 
		Flight flight3 = new Flight("C101","AMS",LocalTime.of(13,00),LocalTime.of(18, 30),"DEL",800.00);
			List<Flight> flightList = new ArrayList<>();
			flightList.add(flight1);
			flightList.add(flight2);
			flightList.add(flight3);
		List<FlightDto> listDto = flightList.stream().map(flight-> model.map(flight, FlightDto.class)).collect(Collectors.toList());
	
		Mockito.when(service.findAll(anyString(),anyString(),any(),any())).thenReturn(listDto);
		
		
		mockMvc.perform(MockMvcRequestBuilders.get("/flight/api/all/")
				.param("origin","AMS")
				.param("destination","DEL")
				.param("sortField", "PRICE")
				.param("sortOrder","ASC"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.size", listDto.size()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(750.00))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(800.00))
				.andExpect(MockMvcResultMatchers.jsonPath("$[2].price").value(850.00));
				
				

		//assertEquals(listDto.size(),3);
		//assertEquals(listDto.get(0).getFlightNumber(),"A101");
	}


	


}
