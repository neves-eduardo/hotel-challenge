package com.neves.eduardo.desafio.reservationservice.service;

import com.neves.eduardo.desafio.reservationservice.client.HotelClient;
import com.neves.eduardo.desafio.reservationservice.converter.ReservationDTOtoReservationEntity;
import com.neves.eduardo.desafio.reservationservice.converter.ReservationEntityToReservationDTO;
import com.neves.eduardo.desafio.reservationservice.dto.ReservationDTO;
import com.neves.eduardo.desafio.reservationservice.dto.hotel.HotelDTO;
import com.neves.eduardo.desafio.reservationservice.dto.hotel.HotelRoomDTO;
import com.neves.eduardo.desafio.reservationservice.entity.Reservation;
import com.neves.eduardo.desafio.reservationservice.exception.RoomNotAvailableException;
import com.neves.eduardo.desafio.reservationservice.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepository repository;

    @Mock
    private HotelClient client;

    @Mock
    private ReservationDTOtoReservationEntity reservationDTOtoReservationEntity;

    @Mock
    private ReservationEntityToReservationDTO reservationEntityToReservationDTO;

    @InjectMocks
    private ReservationService reservationService;

    private ReservationDTO reservationDTO;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        reservationDTO = new ReservationDTO();
        reservation = new Reservation();
    }

    @Test
    void create_success() {
        String hotelId = "hotel1";
        String roomId = "room1";
        LocalDateTime checkInDate = LocalDateTime.now().plusDays(1);
        LocalDateTime checkOutDate = LocalDateTime.now().plusDays(2);

        reservationDTO.setHotelId(hotelId);
        reservationDTO.setRoomId(roomId);
        reservationDTO.setCheckInDate(checkInDate);
        reservationDTO.setCheckOutDate(checkOutDate);

        HotelDTO hotelDTO = new HotelDTO();
        HotelRoomDTO hotelRoomDTO = new HotelRoomDTO();
        hotelRoomDTO.setId(roomId);
        hotelDTO.setRooms(Collections.singletonList(hotelRoomDTO));

        when(client.getHotelById(hotelId)).thenReturn(Mono.just(hotelDTO));
        when(repository.findByHotelIdAndRoomId(hotelId, roomId)).thenReturn(Flux.empty());
        when(reservationDTOtoReservationEntity.map(reservationDTO)).thenReturn(reservation);
        when(repository.save(any(Reservation.class))).thenReturn(Mono.just(reservation));
        when(reservationEntityToReservationDTO.map(any(Reservation.class))).thenReturn(reservationDTO);

        StepVerifier.create(reservationService.create(reservationDTO))
                .expectNext(reservationDTO)
                .verifyComplete();

        verify(client).getHotelById(hotelId);
        verify(repository).findByHotelIdAndRoomId(hotelId, roomId);
        verify(repository).save(reservation);
    }

    @Test
    void create_roomNotAvailable() {
        String hotelId = "hotel1";
        String roomId = "room1";
        LocalDateTime checkInDate = LocalDateTime.now().plusDays(1);
        LocalDateTime checkOutDate = LocalDateTime.now().plusDays(2);

        reservationDTO.setHotelId(hotelId);
        reservationDTO.setRoomId(roomId);
        reservationDTO.setCheckInDate(checkInDate);
        reservationDTO.setCheckOutDate(checkOutDate);

        HotelDTO hotelDTO = new HotelDTO();
        HotelRoomDTO hotelRoomDTO = new HotelRoomDTO();
        hotelRoomDTO.setId(roomId);
        hotelDTO.setRooms(Collections.singletonList(hotelRoomDTO));

        Reservation existingReservation = new Reservation();
        existingReservation.setCheckInDate(checkInDate.minusDays(1));
        existingReservation.setCheckOutDate(checkOutDate.plusDays(1));

        when(client.getHotelById(hotelId)).thenReturn(Mono.just(hotelDTO));
        when(repository.findByHotelIdAndRoomId(hotelId, roomId)).thenReturn(Flux.just(existingReservation));

        StepVerifier.create(reservationService.create(reservationDTO))
                .expectError(RoomNotAvailableException.class)
                .verify();

        verify(client).getHotelById(hotelId);
        verify(repository).findByHotelIdAndRoomId(hotelId, roomId);
        verify(repository, never()).save(any(Reservation.class));
    }

    @Test
    void update_success() {
        when(reservationDTOtoReservationEntity.map(any(ReservationDTO.class))).thenReturn(reservation);
        when(repository.save(any(Reservation.class))).thenReturn(Mono.just(reservation));
        when(reservationEntityToReservationDTO.map(any(Reservation.class))).thenReturn(reservationDTO);

        StepVerifier.create(reservationService.update(reservationDTO))
                .expectNext(reservationDTO)
                .verifyComplete();

        verify(repository).save(reservation);
    }

    @Test
    void findById_success() {
        when(repository.findById(anyString())).thenReturn(Mono.just(reservation));
        when(reservationEntityToReservationDTO.map(any(Reservation.class))).thenReturn(reservationDTO);

        StepVerifier.create(reservationService.findById("1"))
                .expectNext(reservationDTO)
                .verifyComplete();

        verify(repository).findById("1");
    }

    @Test
    void deleteReservation_success() {
        when(repository.deleteById(anyString())).thenReturn(Mono.empty());

        StepVerifier.create(reservationService.deleteReservation("1"))
                .verifyComplete();

        verify(repository).deleteById("1");
    }

    @Test
    void findAll_success() {
        when(repository.findAll()).thenReturn(Flux.just(reservation));
        when(reservationEntityToReservationDTO.map(any(Reservation.class))).thenReturn(reservationDTO);

        StepVerifier.create(reservationService.findAll())
                .expectNext(reservationDTO)
                .verifyComplete();

        verify(repository).findAll();
    }

    @Test
    void getRoomFromHotel_success() {
        HotelDTO hotelDTO = new HotelDTO();
        HotelRoomDTO hotelRoomDTO = new HotelRoomDTO();
        hotelRoomDTO.setId("room1");
        hotelDTO.setRooms(Collections.singletonList(hotelRoomDTO));

        StepVerifier.create(reservationService.getRoomFromHotel(hotelDTO, "room1"))
                .expectNext(hotelRoomDTO)
                .verifyComplete();
    }


    @Test
    void checkRoomAvailability_roomAvailable() {
        when(repository.findByHotelIdAndRoomId(anyString(), anyString())).thenReturn(Flux.empty());

        StepVerifier.create(reservationService.checkRoomAvailability("hotel1", "room1", LocalDateTime.now(), LocalDateTime.now().plusDays(1)))
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void checkRoomAvailability_roomNotAvailable() {
        Reservation existingReservation = new Reservation();
        existingReservation.setCheckInDate(LocalDateTime.now().minusDays(1));
        existingReservation.setCheckOutDate(LocalDateTime.now().plusDays(1));

        when(repository.findByHotelIdAndRoomId(anyString(), anyString())).thenReturn(Flux.just(existingReservation));

        StepVerifier.create(reservationService.checkRoomAvailability("hotel1", "room1", LocalDateTime.now(), LocalDateTime.now().plusDays(1)))
                .expectNext(false)
                .verifyComplete();
    }

    @Test
    void saveReservation_success() {
        when(reservationDTOtoReservationEntity.map(any(ReservationDTO.class))).thenReturn(reservation);
        when(repository.save(any(Reservation.class))).thenReturn(Mono.just(reservation));
        when(reservationEntityToReservationDTO.map(any(Reservation.class))).thenReturn(reservationDTO);

        StepVerifier.create(reservationService.saveReservation(reservationDTO))
                .expectNext(reservationDTO)
                .verifyComplete();

        verify(repository).save(reservation);
    }

    @Test
    void datesOverlap_noOverlap() {
        LocalDateTime existingCheckIn = LocalDateTime.now();
        LocalDateTime existingCheckOut = LocalDateTime.now().plusDays(1);
        LocalDateTime newCheckIn = LocalDateTime.now().plusDays(2);
        LocalDateTime newCheckOut = LocalDateTime.now().plusDays(3);

        assertFalse(reservationService.datesOverlap(existingCheckIn, existingCheckOut, newCheckIn, newCheckOut));
    }

    @Test
    void datesOverlap_overlap() {
        LocalDateTime existingCheckIn = LocalDateTime.now();
        LocalDateTime existingCheckOut = LocalDateTime.now().plusDays(3);
        LocalDateTime newCheckIn = LocalDateTime.now().plusDays(1);
        LocalDateTime newCheckOut = LocalDateTime.now().plusDays(2);

        assertTrue(reservationService.datesOverlap(existingCheckIn, existingCheckOut, newCheckIn, newCheckOut));
    }

}