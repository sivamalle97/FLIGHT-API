package com.flight.flightApi.Advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {
	
	
	@ExceptionHandler(DataNotFoundInDbException.class)
	public ResponseEntity<String> handleDataNotFound(DataNotFoundInDbException dataNotFoundInDbException){
		return new ResponseEntity<String>(dataNotFoundInDbException.getMessage(),HttpStatus.NOT_FOUND);
	}

	/*
	 * @ExceptionHandler(NullPointerException.class) public ResponseEntity<String>
	 * handleNullPointer(NullPointerException nullPointerException){ return new
	 * ResponseEntity<String>("Enter valid data",HttpStatus.INTERNAL_SERVER_ERROR);
	 * }
	 */
	
	@ExceptionHandler(FiledNotFoundException.class)
	public ResponseEntity<String> handleFiledNotFoundException(FiledNotFoundException filedNotFoundException){
		return new ResponseEntity<String>(filedNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
	}
}
