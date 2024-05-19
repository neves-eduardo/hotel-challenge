package com.neves.eduardo.desafio.hotelservice.dto;

import jakarta.validation.constraints.NotNull;
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

    private String id;
    private String type;
    private List<String> roomAmenities;

    @NotNull
    private Integer capacity;

    @NotNull
    private BigDecimal price;

}
