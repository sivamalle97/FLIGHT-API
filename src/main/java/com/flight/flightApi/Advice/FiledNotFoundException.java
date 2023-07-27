package com.flight.flightApi.Advice;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Setter
@Getter
public class FiledNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String message;
	
	public FiledNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public FiledNotFoundException(String message) {
		super(message);
		this.message = message;
	}
	
	

}
