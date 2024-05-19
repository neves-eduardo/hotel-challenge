package com.neves.eduardo.desafio.reservationservice.repository;

import com.neves.eduardo.desafio.reservationservice.entity.Reservation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ReservationRepository extends ReactiveMongoRepository<Reservation, String> {

    Flux<Reservation> findByHotelIdAndRoomId(String hotelId, String roomId);

}
