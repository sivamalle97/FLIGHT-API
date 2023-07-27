package com.flight.flightApi;

import java.time.LocalTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.flight.flightApi.model.Flight;
import com.flight.flightApi.repository.FlightRepository;

@SpringBootApplication
public class FlightApiApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(FlightApiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Autowired
	private FlightRepository repo;
	
	@Override
	public void run(String... args) throws Exception {
		repo.save(new Flight("A101","AMS",LocalTime.of(11, 00),LocalTime.of(17, 00),"DEL",850));
		repo.save(new Flight("B101","AMS",LocalTime.of(12, 00),LocalTime.of(19, 30),"BOM",750));
		repo.save(new Flight("C101","AMS",LocalTime.of(13, 00),LocalTime.of(18, 30),"BLR",800));
		repo.save(new Flight("D101","AMS",LocalTime.of(9, 00),LocalTime.of(15, 00), "MAA",600));
		repo.save(new Flight("E101","MAA",LocalTime.of(16, 00),LocalTime.of(17, 30),"BOM",100));
		repo.save(new Flight("F101","BOM",LocalTime.of(20, 30),LocalTime.of(21, 30),"DEL",80));
		repo.save(new Flight("G101","BOM",LocalTime.of(18, 00),LocalTime.of(19, 30),"DEL",100));
		repo.save(new Flight("A201","LHR",LocalTime.of(11, 30),LocalTime.of(17, 00),"DEL",600));
		repo.save(new Flight("B201","LHR",LocalTime.of(12, 35),LocalTime.of(19, 30),"BOM",750));
		repo.save(new Flight("C201","LHR",LocalTime.of(13, 45),LocalTime.of(18, 30),"BLR",800));
		repo.save(new Flight("D201","LHR",LocalTime.of(9, 00),LocalTime.of(15, 00), "MAA",600));
		repo.save(new Flight("E201","DEL",LocalTime.of(18, 45),LocalTime.of(20, 15),"BOM",100));
		repo.save(new Flight("F201","BOM",LocalTime.of(21, 15),LocalTime.of(22, 30),"DEL",80));
		repo.save(new Flight("G01","BOM",LocalTime.of(20, 20),LocalTime.of(21, 30), "DEL",100));	
	
	}
	
}
