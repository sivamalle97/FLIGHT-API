package com.flight.flightApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flight.flightApi.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Integer> {
	

	List<Flight> findByOriginAndDestination(String origin, String destination );

}
