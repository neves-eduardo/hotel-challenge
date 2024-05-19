package com.neves.eduardo.desafio.reservationservice.dto.hotel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelRoomDTO {

    private String id;
    private Integer capacity;
    private BigDecimal price;

}
