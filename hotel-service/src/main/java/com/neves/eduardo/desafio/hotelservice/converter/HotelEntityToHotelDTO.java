package com.neves.eduardo.desafio.hotelservice.converter;

import com.neves.eduardo.desafio.hotelservice.dto.HotelDTO;
import com.neves.eduardo.desafio.hotelservice.dto.HotelLocationDTO;
import com.neves.eduardo.desafio.hotelservice.entity.Hotel;
import com.neves.eduardo.desafio.hotelservice.entity.HotelLocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import static java.util.Objects.isNull;

@Mapper(componentModel = "spring")
public interface HotelEntityToHotelDTO {

    @Mapping(target = "location", source = "hotel.location", qualifiedByName = "toCoordinates")
    HotelDTO map(Hotel hotel);

    @Named("toCoordinates")
    default HotelLocationDTO toCoordinates(HotelLocation location) {

        if (isNull(location)) return null;

        return HotelLocationDTO.builder()
                .address(location.getAddress())
                .city(location.getCity())
                .country(location.getCountry())
                .longitude(location.getCoordinates() != null ? location.getCoordinates().getX() : null)
                .latitude(location.getCoordinates() != null ? location.getCoordinates().getY() : null)
                .build();
    }

}
