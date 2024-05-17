package com.neves.eduardo.desafio.hotelservice.controller;

import com.neves.eduardo.desafio.hotelservice.dto.HotelDTO;
import com.neves.eduardo.desafio.hotelservice.dto.HotelSearchCriteriaDTO;
import com.neves.eduardo.desafio.hotelservice.entity.Hotel;
import com.neves.eduardo.desafio.hotelservice.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Flux<HotelDTO> findAllHotels() {
        return hotelService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<HotelDTO> findHotel(String id) {
        return hotelService.find(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteHotel(@PathVariable String id) {
        return hotelService.deleteHotel(id);
    }

    @GetMapping("/search")
    public Flux<Hotel> findHotelsByCriteria(HotelSearchCriteriaDTO criteria) {
        return hotelService.findHotelsByCriteria(criteria);
    }

    @GetMapping("near")
    public Flux<HotelDTO> findAllHotelsNear(@RequestParam Double longitude, @RequestParam Double latitude, @RequestParam(required = false) Double maxDistance) {
        return hotelService.findHotelNear(longitude, latitude, maxDistance);
    }

}
