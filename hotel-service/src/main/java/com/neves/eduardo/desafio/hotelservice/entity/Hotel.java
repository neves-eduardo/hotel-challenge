package com.neves.eduardo.desafio.hotelservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "hotels")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    private String id;
    private String name;
    private HotelLocation location;
    private List<HotelRoom> rooms;
    private List<String> amenities;
    private List<HotelReview> reviews;
    private Double averageRating;

}
