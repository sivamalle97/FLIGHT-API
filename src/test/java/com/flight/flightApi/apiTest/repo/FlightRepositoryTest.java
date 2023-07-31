package com.flight.flightApi.apiTest.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.flight.flightApi.Entity.Flight;
import com.flight.flightApi.repository.FlightRepository;

@DataJpaTest
public class FlightRepositoryTest {

	@Autowired
	private FlightRepository repo;

	@Test
	public void testFindByOriginAndDestination(){
		Flight flight1 = new Flight("A101", "AMS",LocalTime.of(11, 00),LocalTime.of(17, 00),"DEL",850.00);
		Flight flight2 = new Flight("B101","AMS",LocalTime.of(12, 00),LocalTime.of(19, 30),"BOM",750.00);
		Flight flight3 = new Flight("C101","AMS",LocalTime.of(13, 00),LocalTime.of(18, 30),"BLR",800.00);
		
		
		
		repo.save(flight1);
		repo.save(flight2);
		repo.save(flight3);
		List<Flight> flightNew = this.repo.findByOriginAndDestination(flight1.getOrigin(),flight1.getDestination());

		// then - verify the output
		assertThat(flightNew).size().isNotNull();
	}
}
