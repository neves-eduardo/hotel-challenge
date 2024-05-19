package com.neves.eduardo.desafio.reservationservice.service;

import com.neves.eduardo.desafio.reservationservice.client.HotelClient;
import com.neves.eduardo.desafio.reservationservice.converter.ReservationDTOtoReservationEntity;
import com.neves.eduardo.desafio.reservationservice.converter.ReservationEntityToReservationDTO;
import com.neves.eduardo.desafio.reservationservice.dto.ReservationDTO;
import com.neves.eduardo.desafio.reservationservice.dto.hotel.HotelDTO;
import com.neves.eduardo.desafio.reservationservice.dto.hotel.HotelRoomDTO;
import com.neves.eduardo.desafio.reservationservice.entity.Reservation;
import com.neves.eduardo.desafio.reservationservice.exception.RoomNotAvailableException;
import com.neves.eduardo.desafio.reservationservice.exception.RoomNotFoundException;
import com.neves.eduardo.desafio.reservationservice.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository repository;
    private final HotelClient client;
    private final ReservationDTOtoReservationEntity reservationDTOtoReservationEntity;
    private final ReservationEntityToReservationDTO reservationEntityToReservationDTO;

    public Mono<ReservationDTO> create(ReservationDTO reservationDTO) {
        String hotelId = reservationDTO.getHotelId();
        String roomId = reservationDTO.getRoomId();
        LocalDateTime checkInDate = reservationDTO.getCheckInDate();
        LocalDateTime checkOutDate = reservationDTO.getCheckOutDate();

        return client.getHotelById(hotelId)
                .flatMap(hotelDTO -> getRoomFromHotel(hotelDTO, roomId))
                .flatMap(room -> checkRoomAvailability(hotelId, roomId, checkInDate, checkOutDate))
                .flatMap(isAvailable -> {
                    if (!isAvailable) {
                        return Mono.error(new RoomNotAvailableException("Room is not available"));
                    }
                    return saveReservation(reservationDTO);
                });
    }

    public Mono<Void> deleteReservation(String id) {
        return repository.deleteById(id);
    }

    public Flux<ReservationDTO> findAll() {
        return repository.findAll().map(reservationEntityToReservationDTO::map);
    }

    private Mono<HotelRoomDTO> getRoomFromHotel(HotelDTO hotelDTO, String roomId) {
        return Mono.justOrEmpty(hotelDTO.getRooms().stream()
                        .filter(room -> room.getId().equals(roomId))
                        .findFirst())
                .switchIfEmpty(Mono.error(new RoomNotFoundException("Room not found")));
    }

    private Mono<Boolean> checkRoomAvailability(String hotelId, String roomId, LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        return repository.findByHotelIdAndRoomId(hotelId, roomId)
                .filter(reservation -> datesOverlap(reservation.getCheckInDate(), reservation.getCheckOutDate(), checkInDate, checkOutDate))
                .collectList()
                .map(List::isEmpty);
    }

    private Mono<ReservationDTO> saveReservation(ReservationDTO reservationDTO) {
        Reservation reservationEntity = reservationDTOtoReservationEntity.map(reservationDTO);
        return repository.save(reservationEntity)
                .map(reservationEntityToReservationDTO::map);
    }

    private boolean datesOverlap(LocalDateTime existingCheckIn, LocalDateTime existingCheckOut, LocalDateTime newCheckIn, LocalDateTime newCheckOut) {
        return (newCheckIn.isBefore(existingCheckOut) && newCheckOut.isAfter(existingCheckIn));
    }

}
