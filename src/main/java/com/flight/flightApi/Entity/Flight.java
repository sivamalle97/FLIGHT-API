package com.flight.flightApi.Entity;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Flight {


	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String flightNumber;
	
	@NotBlank
	private String origin;
	
	@NotBlank
	private String destination;
	
	private LocalTime departureTime;
	
	private LocalTime arrivalTime;
	
	private Double price;

	public Flight(String flightNumber, @NotBlank String origin, LocalTime arrivalTime,
			LocalTime departureTime, @NotBlank String destination, Double price) {
		super();
		this.flightNumber = flightNumber;
		this.origin = origin;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.price = price;
	}

	
		
	

}
