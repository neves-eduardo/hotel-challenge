package com.neves.eduardo.desafio.reservationservice.dto.hotel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelSearchCriteriaDTO {

    private String country;
    private String city;
    private Integer numberOfGuests;
    private List<String> amenities;
    private HotelAvailabilityDateCriteriaDTO hotelAvailabilityDateCriteria;
    private HotelPriceSearchCriteriaDTO hotelPriceSearchCriteria;
    private HotelReviewSearchCriteriaDTO hotelReviewSearchCriteria;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class HotelAvailabilityDateCriteriaDTO {

        private LocalDateTime checkInDate;
        private LocalDateTime checkOutDate;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class HotelPriceSearchCriteriaDTO {

        private BigDecimal minimumPrice;
        private BigDecimal maxPrice;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class HotelReviewSearchCriteriaDTO {

        private Double averageRatingFrom;
        private Double averageRatingTo;

    }

}
