package com.neves.eduardo.desafio.hotelservice.repository;

import com.neves.eduardo.desafio.hotelservice.entity.Hotel;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface HotelRepository extends ReactiveMongoRepository<Hotel, String> {

    @Query("{ location.coordinates: { $near: { $geometry: { type: 'Point', coordinates: ?0 }, $maxDistance: ?1 } } }")
    Flux<Hotel> findHotelNear(Point point, double maxDistance);

}
