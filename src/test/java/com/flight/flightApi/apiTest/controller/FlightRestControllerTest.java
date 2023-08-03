package com.flight.flightApi.apiTest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.flight.flightApi.Entity.Flight;
import com.flight.flightApi.apiTest.config.TestConfig;
import com.flight.flightApi.controller.FlightRestController;
import com.flight.flightApi.dto.FlightDto;
import com.flight.flightApi.enumaration.SortField;
import com.flight.flightApi.enumaration.SortOrder;
import com.flight.flightApi.repository.FlightRepository;
import com.flight.flightApi.service.FlightService;

@EnableWebMvc
@ExtendWith(MockitoExtension.class)
@WebMvcTest(FlightRestController.class)
//@ContextConfiguration(classes = TestConfig.class)
public class FlightRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FlightService service;

	List<Flight> flightList;


	@BeforeEach
	public void setup() {
		Flight flight1 = new Flight(1L,"A101","AMS","DEL",LocalDateTime.of(2023, 8, 2, 11, 0),LocalDateTime.of(2023, 8, 2, 17, 0), 850.00);
		Flight flight2 = new Flight(2L,"B101","AMS","DEL",LocalDateTime.of(2023, 8, 2, 12, 0),LocalDateTime.of(2023, 8, 2, 19, 30),750.00);
		Flight flight3 = new Flight(3L,"C101","AMS","DEL",LocalDateTime.of(2023, 8, 2, 13, 0),LocalDateTime.of(2023, 8, 2, 18, 30),800.00);
		flightList = new ArrayList<>();
		flightList.add(flight1);
		flightList.add(flight2);
		flightList.add(flight3);
	}

	@Autowired
	private ModelMapper model;


	@Test
	public void testFindAll() throws Exception {
		List<FlightDto> listDto = flightList.stream().map(flight-> model.map(flight, FlightDto.class)).collect(Collectors.toList());
		Mockito.when(service.findAll(anyString(),anyString(),any(),any())).thenReturn(listDto);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/flight/api/all/AMS/DEL")
				.queryParam("sortField", SortField.PRICE.name())
				.queryParam("sortOrder", SortOrder.ASC.name()))

		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.[0].price").value(750.00));
		//.andExpect(MockMvcResultMatchers.jsonPath("$[0].price", Matchers.is(listDto.get(0).getPrice())));
		//.andExpect(MockMvcResultMatchers.);
		//.andDo(MockMvcResultHandlers.print());
		
		Assertions.assertNotNull(listDto.get(0));
		//assertEquals(850.00,listDto.get(0).getPrice());

	}

	@Test
	public void testFindAllDurationDesc() throws Exception {
		List<FlightDto> listDto = flightList.stream().map(flight-> model.map(flight, FlightDto.class)).collect(Collectors.toList());
		Mockito.when(service.findAll(anyString(),anyString(),any(),any())).thenReturn(listDto);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/flight/api/all/AMS/DEL")
				.queryParam("sortField", SortField.DURATION.name())
				.queryParam("sortOrder", SortOrder.DESC.name()))

		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.[1].price").value(800.00));
		//.andExpect(MockMvcResultMatchers.jsonPath("$[0].price", Matchers.is(listDto.get(0).getPrice())));
		//.andExpect(MockMvcResultMatchers.);
		//.andDo(MockMvcResultHandlers.print());
		
		Assertions.assertNotNull(listDto.get(0));
		//assertEquals(850.00,listDto.get(0).getPrice());

	}




}
