package com.neves.eduardo.desafio.hotelservice.controller;

import com.neves.eduardo.desafio.hotelservice.dto.HotelDTO;
import com.neves.eduardo.desafio.hotelservice.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public Mono<HotelDTO> createHotel(@RequestBody HotelDTO hotel) {
        return hotelService.create(hotel);
    }

    @GetMapping
    public Flux<HotelDTO> getHotels() {
        return hotelService.findAll();
    }

    @GetMapping("near")
    public Flux<HotelDTO> getHotels(@RequestParam Double longitude, @RequestParam Double latitude, @RequestParam(required = false) Double maxDistance) {
        return hotelService.findHotelNear(longitude, latitude, maxDistance);
    }

}
