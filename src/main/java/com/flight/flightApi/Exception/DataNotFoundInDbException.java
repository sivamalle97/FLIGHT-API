package com.flight.flightApi.Exception;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Setter
@Getter
//@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundInDbException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;


	public DataNotFoundInDbException() {

	}
	public DataNotFoundInDbException(String msg) {
		super(msg);
		this.message=msg;
		
	}

}
