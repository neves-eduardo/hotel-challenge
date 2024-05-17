package com.neves.eduardo.desafio.hotelservice.service;

import com.neves.eduardo.desafio.hotelservice.converter.HotelDTOToHotelEntityConverter;
import com.neves.eduardo.desafio.hotelservice.converter.HotelEntityToHotelDTO;
import com.neves.eduardo.desafio.hotelservice.dto.HotelDTO;
import com.neves.eduardo.desafio.hotelservice.dto.HotelSearchCriteriaDTO;
import com.neves.eduardo.desafio.hotelservice.entity.Hotel;
import com.neves.eduardo.desafio.hotelservice.repository.CustomHotelRepository;
import com.neves.eduardo.desafio.hotelservice.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository repository;
    private final HotelDTOToHotelEntityConverter hotelDTOtoHotelEntityConverter;
    private final HotelEntityToHotelDTO hotelEntityToHotelDTO;
    private final CustomHotelRepository customHotelRepository;
    private static final double DEFAULT_DISTANCE = 3000.0;

    public Mono<HotelDTO> create(HotelDTO hotelDTO) {
        Hotel hotelEntity = hotelDTOtoHotelEntityConverter.map(hotelDTO);
        return repository.save(hotelEntity).map(hotelEntityToHotelDTO::map);
    }

    public Mono<HotelDTO> find(String id) {
        return repository.findById(id).map(hotelEntityToHotelDTO::map);
    }

    public Flux<HotelDTO> findAll() {
        return repository.findAll().map(hotelEntityToHotelDTO::map);
    }

    public Flux<HotelDTO> findHotelNear(Double longitude, Double latitude, Double maxDistance) {
        if (isNull(maxDistance)) maxDistance = DEFAULT_DISTANCE;

        Point point = new Point(longitude, latitude);
        return repository.findHotelNear(point, maxDistance)
                .map(hotelEntityToHotelDTO::map);
    }

    public Mono<Void> deleteHotel(String id) {
        return repository.deleteById(id);
    }

    public Flux<Hotel> findHotelsByCriteria(HotelSearchCriteriaDTO criteria) {
        return customHotelRepository.searchHotels(
                criteria.getDestination(),
                criteria.getCheckInDate(),
                criteria.getCheckOutDate(),
                criteria.getNumberOfRooms(),
                criteria.getNumberOfGuests()
        );
    }
}
