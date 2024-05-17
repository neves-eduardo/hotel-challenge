package com.neves.eduardo.desafio.hotelservice.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelLocationDTO {

    private String city;
    private String address;
    private String country;
    private Double latitude;
    private Double longitude;

}
