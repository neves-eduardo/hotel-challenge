package com.neves.eduardo.desafio.hotelservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelAvailabilityDateCriteriaDTO {

    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;

}
