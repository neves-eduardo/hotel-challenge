package com.neves.eduardo.desafio.hotelservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelRoomDTO {

    @JsonIgnore
    private ObjectId id;
    private String type;
    private List<String> roomAmenities;

    @NotEmpty(message = "room.capacity is mandatory")
    private Integer capacity;

    @NotNull(message = "room.price is mandatory")
    private BigDecimal price;

}
