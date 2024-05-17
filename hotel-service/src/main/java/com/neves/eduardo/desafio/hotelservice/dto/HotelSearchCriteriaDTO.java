package com.neves.eduardo.desafio.hotelservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelSearchCriteriaDTO {

    private String country;
    private String city;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numberOfGuests;

}
