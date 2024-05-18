package com.neves.eduardo.desafio.hotelservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.List;

@Data
public class HotelRoom {

    @Id
    private ObjectId id;
    private String type;
    private Integer capacity;
    private BigDecimal price;
    private List<String> roomAmenities;

    public HotelRoom( String type, Integer capacity, BigDecimal price, List<String> roomAmenities) {
        this.id = new ObjectId();
        this.type = type;
        this.capacity = capacity;
        this.price = price;
        this.roomAmenities = roomAmenities;
    }

}
