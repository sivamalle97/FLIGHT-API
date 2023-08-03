package com.flight.flightApi.Entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor

@AllArgsConstructor
@Data
public class Flight {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@NotNull
	private String flightNumber;
	
	@NotBlank
	@NotNull
	private String origin;
	
	@NotBlank
	@NotNull
	private String destination;
	
	private LocalDateTime departureTime;
	
	private LocalDateTime arrivalTime;
	
	private Double price;

	

	
		
	

}
