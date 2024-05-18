package com.neves.eduardo.desafio.hotelservice.controller;

import com.neves.eduardo.desafio.hotelservice.dto.HotelDTO;
import com.neves.eduardo.desafio.hotelservice.dto.HotelReviewDTO;
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

    @PostMapping("/search")
    public Flux<Hotel> findHotelsByCriteria(@RequestBody HotelSearchCriteriaDTO criteria) {
        return hotelService.findHotelsByCriteria(criteria);
    }

    @PostMapping("/{id}/reviews")
    public Mono<HotelDTO> createReview(@PathVariable String id, @RequestBody HotelReviewDTO reviewDTO) {
        return hotelService.createHotelRating(id, reviewDTO);
    }

}
