package com.neves.eduardo.desafio.hotelservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

}
