package com.neves.eduardo.desafio.reservationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
@ComponentScan(basePackages = {"com.neves.eduardo.desafio.reservationservice"})
public class ReservationserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationserviceApplication.class, args);
	}

}
