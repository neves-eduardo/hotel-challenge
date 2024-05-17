package com.neves.eduardo.desafio.hotelservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelRoom {

    private String type;
    private Integer capacity;
    private BigDecimal price;
    private List<HotelRoomAvailability> availability;

}
