package com.neves.eduardo.desafio.hotelservice.controller;

import com.neves.eduardo.desafio.hotelservice.dto.HotelDTO;
import com.neves.eduardo.desafio.hotelservice.dto.HotelReviewDTO;
import com.neves.eduardo.desafio.hotelservice.dto.HotelSearchCriteriaDTO;
import com.neves.eduardo.desafio.hotelservice.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public Mono<HotelDTO> createHotel(@RequestBody HotelDTO hotel) {
        log.info(String.format("[hotel-service] [controller] received hotel creation request. [%s]", hotel));
        return hotelService.create(hotel);
    }

    @GetMapping
    public Flux<HotelDTO> findAllHotels() {
        log.info("[hotel-service] [controller] received request to find all hotels");
        return hotelService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<HotelDTO> findHotel(String id) {
        log.info(String.format("[hotel-service] [controller] received request to find hotel with id. [%s]", id));
        return hotelService.find(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteHotel(@PathVariable String id) {
        log.info(String.format("[hotel-service] [controller] received request to delete  hotel with id. [%s]", id));
        return hotelService.deleteHotel(id);
    }

    @PostMapping("/search")
    public Flux<HotelDTO> findHotelsByCriteria(@RequestBody HotelSearchCriteriaDTO criteria) {
        log.info(String.format("[hotel-service] [controller] received request to search hotels with criteria [%s].", criteria));
        return hotelService.findHotelsByCriteria(criteria);
    }

    @PostMapping("/{id}/reviews")
    public Mono<HotelDTO> createReview(@PathVariable String id, @RequestBody HotelReviewDTO reviewDTO) {
        log.info(String.format("[hotel-service] [controller] received request to create a new review (body: [%s]) for hotel with id [%s].", reviewDTO, id));
        return hotelService.createHotelRating(id, reviewDTO);
    }

}
