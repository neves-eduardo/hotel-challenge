package com.neves.eduardo.desafio.hotelservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelPriceSearchCriteriaDTO {

    private BigDecimal minimumPrice;
    private BigDecimal maxPrice;

}
