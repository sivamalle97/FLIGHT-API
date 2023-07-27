package com.flight.flightApi.model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Flight {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String flightNumber;
	
	@NotBlank
	private String origin;
	
	@NotBlank
	private String destination;
	
	@JsonFormat(pattern="HH:mm")
	private LocalTime depatureTime;
	
	@JsonFormat(pattern="HH:mm")
	private LocalTime arrivalTime;
	
	private Integer price;
	
	
	
	
	

	public Flight(String flightNumber, String origin, LocalTime depatureTime, LocalTime arrivalTime, String destination,
			Integer price) {
		super();
		this.flightNumber = flightNumber;
		this.origin = origin;
		this.destination = destination;
		this.depatureTime = depatureTime;
		this.arrivalTime = arrivalTime;
		this.price = price;
		
			
	}


	public Flight() {
		super();
	}

	
	

}
