package com.neves.eduardo.desafio.hotelservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
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
    private List<String> amenities;
    private List<HotelReviewDTO> reviews;

    @NotNull
    private String name;

    @NotNull
    private HotelLocationDTO location;

    @NotNull
    private List<HotelRoomDTO> rooms;

    @JsonIgnore
    private Double averageRating;

}
