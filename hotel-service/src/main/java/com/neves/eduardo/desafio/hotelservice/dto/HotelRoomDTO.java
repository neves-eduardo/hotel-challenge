package com.neves.eduardo.desafio.hotelservice.dto;

import com.neves.eduardo.desafio.hotelservice.entity.HotelRoomAvailability;
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
public class HotelRoomDTO {

    private String type;
    private Integer capacity;
    private BigDecimal price;
    private List<HotelRoomAvailabilityDTO> availability;

}
