package com.neves.eduardo.desafio.hotelservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @JsonIgnore
    private String id;
    private List<String> amenities;
    private List<HotelReviewDTO> reviews;

    @NotNull
    @NotBlank(message = "Hotel name is mandatory")
    @Size(max = 100, message = "Hotel name should not exceed 100 characters")
    private String name;

    @NotNull(message = "Location is mandatory")
    private HotelLocationDTO location;

    @NotNull
    @NotEmpty(message = "Rooms is mandatory")
    private List<HotelRoomDTO> rooms;

    @JsonIgnore
    private Double averageRating;

}
