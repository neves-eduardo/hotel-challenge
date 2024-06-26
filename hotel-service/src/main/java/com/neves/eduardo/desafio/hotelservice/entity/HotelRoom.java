package com.neves.eduardo.desafio.hotelservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class HotelRoom {

    private String id;
    private String type;
    private Integer capacity;
    private BigDecimal price;
    private List<String> roomAmenities;

}
