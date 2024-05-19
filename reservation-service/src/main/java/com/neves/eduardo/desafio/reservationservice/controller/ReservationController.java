package com.neves.eduardo.desafio.reservationservice.controller;

import com.neves.eduardo.desafio.reservationservice.dto.ReservationDTO;
import com.neves.eduardo.desafio.reservationservice.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService service;

    @PostMapping
    public Mono<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        return service.create(reservationDTO);
    }

    @GetMapping
    public Flux<ReservationDTO> getAllReservations() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteHotel(@PathVariable String id) {
        return service.deleteReservation(id);
    }

}
