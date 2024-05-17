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

    public Flux<Hotel> searchHotels(String country, String city, LocalDate checkInDate, LocalDate checkOutDate, Integer numberOfGuests) {
        Criteria criteria = new Criteria();

        if (country != null && !country.isEmpty()) {
            criteria.and("location.country").is(country);
        }
        if (city != null && !city.isEmpty()) {
            criteria.and("location.city").is(city);
        }
        if (checkInDate != null) {
            criteria.and("rooms.availability.checkIn").lte(checkInDate);
        }
        if (checkOutDate != null) {
            criteria.and("rooms.availability.checkOut").gte(checkOutDate);
        }
        if (numberOfGuests != null && numberOfGuests > 0) {
            criteria.and("rooms.capacity").gte(numberOfGuests);
        }

        Query query = new Query(criteria);
        return mongoTemplate.find(query, Hotel.class);
    }

}
