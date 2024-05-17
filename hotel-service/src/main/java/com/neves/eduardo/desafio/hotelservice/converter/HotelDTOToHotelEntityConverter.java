package com.neves.eduardo.desafio.hotelservice.converter;

import com.neves.eduardo.desafio.hotelservice.dto.HotelDTO;
import com.neves.eduardo.desafio.hotelservice.dto.HotelLocationDTO;
import com.neves.eduardo.desafio.hotelservice.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Mapper(componentModel = "spring")
public interface HotelDTOToHotelEntityConverter {

    @Mapping(target = "location.coordinates", source = "hotelDTO.location", qualifiedByName = "toCoordinates")
    Hotel map(HotelDTO hotelDTO);

    @Named("toCoordinates")
    default GeoJsonPoint toCoordinates(HotelLocationDTO location) {
        return new GeoJsonPoint(location.getLatitude(), location.getLongitude());
    }

}
