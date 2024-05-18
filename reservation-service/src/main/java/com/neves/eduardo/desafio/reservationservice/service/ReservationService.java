package com.neves.eduardo.desafio.reservationservice.service;

import com.neves.eduardo.desafio.reservationservice.converter.ReservationDTOtoReservationEntity;
import com.neves.eduardo.desafio.reservationservice.converter.ReservationEntityToReservationDTO;
import com.neves.eduardo.desafio.reservationservice.dto.ReservationDTO;
import com.neves.eduardo.desafio.reservationservice.entity.Reservation;
import com.neves.eduardo.desafio.reservationservice.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository repository;
    private final ReservationDTOtoReservationEntity reservationDTOtoReservationEntity;
    private final ReservationEntityToReservationDTO reservationEntityToReservationDTO;

    public Mono<ReservationDTO> create(ReservationDTO reservationDTO) {
        Reservation reservationEntity = reservationDTOtoReservationEntity.map(reservationDTO);
        return repository.save(reservationEntity).map(reservationEntityToReservationDTO::map);
    }

    public Mono<Void> deleteReservation(String id) {
        return repository.deleteById(id);
    }

    public Flux<ReservationDTO> findAll() {
        return repository.findAll().map(reservationEntityToReservationDTO::map);
    }
}
