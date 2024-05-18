package com.neves.eduardo.desafio.reservationservice.repository;

import com.neves.eduardo.desafio.reservationservice.entity.Reservation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReservationRepository extends ReactiveMongoRepository<Reservation, String> {
}
