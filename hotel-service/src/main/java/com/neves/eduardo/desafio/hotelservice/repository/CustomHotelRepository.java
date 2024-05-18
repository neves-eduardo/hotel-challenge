package com.neves.eduardo.desafio.hotelservice.repository;

import com.neves.eduardo.desafio.hotelservice.dto.HotelSearchCriteriaDTO;
import com.neves.eduardo.desafio.hotelservice.entity.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class CustomHotelRepository {

    private final ReactiveMongoTemplate mongoTemplate;

    public Flux<Hotel> searchHotels(HotelSearchCriteriaDTO criteriaDTO) {
        Criteria criteria = new Criteria();

        if (criteriaDTO.getCountry() != null && !criteriaDTO.getCountry().isEmpty()) {
            criteria.and("location.country").is(criteriaDTO.getCountry());
        }
        if (criteriaDTO.getCity() != null && !criteriaDTO.getCity().isEmpty()) {
            criteria.and("location.city").is(criteriaDTO.getCity());
        }

        if (criteriaDTO.getNumberOfGuests() != null && criteriaDTO.getNumberOfGuests() > 0) {
            criteria.and("rooms.capacity").gte(criteriaDTO.getNumberOfGuests());
        }

        if (criteriaDTO.getAmenities() != null && !criteriaDTO.getAmenities().isEmpty()) {
            criteria.and("rooms.amenities").in(criteriaDTO.getAmenities());
        }

        if (criteriaDTO.getHotelAvailabilityDateCriteria() != null) {
            LocalDateTime checkInDate = criteriaDTO.getHotelAvailabilityDateCriteria().getCheckInDate();
            LocalDateTime checkOutDate = criteriaDTO.getHotelAvailabilityDateCriteria().getCheckOutDate();
            if (checkInDate != null && checkOutDate != null) {
                criteria.and("reservations.checkInDate").gte(checkOutDate)
                        .orOperator(Criteria.where("reservations.checkOutDate").lte(checkInDate));
            }
        }

        if (criteriaDTO.getHotelPriceSearchCriteria() != null) {
            BigDecimal minPrice = criteriaDTO.getHotelPriceSearchCriteria().getMinimumPrice();
            BigDecimal maxPrice = criteriaDTO.getHotelPriceSearchCriteria().getMinimumPrice();
            if (minPrice != null) {
                criteria.and("rooms.price").gte(minPrice);
            }
            if (maxPrice != null) {
                criteria.and("rooms.price").lte(maxPrice);
            }
        }

        Query query = new Query(criteria);
        return mongoTemplate.find(query, Hotel.class);
    }

}
