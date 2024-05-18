package com.neves.eduardo.desafio.hotelservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelReviewSearchCriteriaDTO {

    private Double averageRatingFrom;
    private Double averageRatingTo;

}
