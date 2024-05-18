package com.neves.eduardo.desafio.hotelservice.repository;

import com.neves.eduardo.desafio.hotelservice.entity.Hotel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface HotelRepository extends ReactiveMongoRepository<Hotel, String> {

}
