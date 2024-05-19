package com.neves.eduardo.desafio.reservationservice.controller;

import com.neves.eduardo.desafio.reservationservice.dto.ReservationDTO;
import com.neves.eduardo.desafio.reservationservice.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService service;

    @PostMapping
    public Mono<ReservationDTO> createReservation(@Valid @RequestBody ReservationDTO reservation) {
        log.info(String.format("[reservation-service] [controller] received reservation creation request. [%s]", reservation));
        return service.create(reservation);
    }

    @GetMapping
    public Flux<ReservationDTO> findAllReservations() {
        log.info("[reservation-service] [controller] received request to find all reservations");
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteHotel(@PathVariable String id) {
        log.info(String.format("[reservation-service] [controller] received request to delete reservation with id. [%s]", id));
        return service.deleteReservation(id);
    }

}
