package com.neves.eduardo.desafio.reservationservice.dto.hotel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO {

    private String id;
    private String name;
    private List<HotelRoomDTO> rooms;

}