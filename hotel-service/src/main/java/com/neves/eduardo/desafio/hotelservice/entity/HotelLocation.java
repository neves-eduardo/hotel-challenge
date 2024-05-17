package com.neves.eduardo.desafio.hotelservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelLocation {

    private String city;
    private String address;
    private String country;
    private GeoJsonPoint coordinates;

}
