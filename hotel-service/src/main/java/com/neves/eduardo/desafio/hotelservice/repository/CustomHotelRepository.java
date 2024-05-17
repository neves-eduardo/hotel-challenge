package com.neves.eduardo.desafio.hotelservice.repository;

import com.neves.eduardo.desafio.hotelservice.entity.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Repository
@RequiredArgsConstructor
public class CustomHotelRepository {

    private final ReactiveMongoTemplate mongoTemplate;

    public Flux<Hotel> searchHotels(String destination, LocalDate checkInDate, LocalDate checkOutDate, Integer numberOfRooms, Integer numberOfGuests) {
        Criteria criteria = new Criteria();

        if (destination != null && !destination.isEmpty()) {
            criteria.and("destination").is(destination);
        }
        if (checkInDate != null) {
            criteria.and("availability.startDate").lte(checkInDate);
        }
        if (checkOutDate != null) {
            criteria.and("availability.endDate").gte(checkOutDate);
        }
        if (numberOfRooms != null && numberOfRooms > 0) {
            criteria.and("rooms.available").gte(numberOfRooms);
        }
        if (numberOfGuests != null && numberOfGuests > 0) {
            criteria.and("rooms.capacity").gte(numberOfGuests);
        }

        Query query = new Query(criteria);
        return mongoTemplate.find(query, Hotel.class);
    }

}
