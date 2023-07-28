package com.flight.flightApi.Entity;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Flight {

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String flightNumber;
	
	@NotBlank
	private String origin;
	
	@NotBlank
	private String destination;
	
	//@Column(name="depatureTime")
	//@JsonFormat(pattern="HH:mm")
	private LocalTime departureTime;
	
	//@Column(name="arrivalTime")
	//@JsonFormat(pattern="HH:mm")
	private LocalTime arrivalTime;
	
	private Integer price;
	

	public Flight(String flightNumber, String origin, LocalTime depatureTime, LocalTime arrivalTime, String destination,
			Integer price) {
		super();
		this.flightNumber = flightNumber;
		this.origin = origin;
		this.destination = destination;
		this.departureTime = depatureTime;
		this.arrivalTime = arrivalTime;
		this.price = price;
		
			
	}


	public Flight() {
		super();
	}

	
	

}
