package com.neves.eduardo.desafio.hotelservice.dto;

import com.neves.eduardo.desafio.hotelservice.entity.HotelLocation;
import com.neves.eduardo.desafio.hotelservice.entity.HotelRoom;
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
    private HotelLocationDTO location;
    private List<HotelRoomDTO> rooms;
    private List<String> amenities;

}
