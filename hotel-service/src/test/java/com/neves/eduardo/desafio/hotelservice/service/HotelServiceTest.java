package com.neves.eduardo.desafio.hotelservice.service;

import com.neves.eduardo.desafio.hotelservice.converter.HotelDTOToHotelEntityConverter;
import com.neves.eduardo.desafio.hotelservice.converter.HotelEntityToHotelDTO;
import com.neves.eduardo.desafio.hotelservice.dto.HotelDTO;
import com.neves.eduardo.desafio.hotelservice.dto.HotelSearchCriteriaDTO;
import com.neves.eduardo.desafio.hotelservice.entity.Hotel;
import com.neves.eduardo.desafio.hotelservice.entity.HotelReview;
import com.neves.eduardo.desafio.hotelservice.exception.HotelNotFoundException;
import com.neves.eduardo.desafio.hotelservice.repository.CustomHotelRepository;
import com.neves.eduardo.desafio.hotelservice.repository.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {

    @Mock
    private HotelRepository repository;

    @Mock
    private HotelEntityToHotelDTO hotelEntityToHotelDTO;

    @Mock
    private CustomHotelRepository customHotelRepository;

    @InjectMocks
    private HotelService hotelService;

    private HotelDTO hotelDTO;
    private Hotel hotel;

    @BeforeEach
    void setUp() {
        hotelDTO = new HotelDTO();
        hotel = new Hotel();
    }

    @Test
    void find_success() {
        when(repository.findById(anyString())).thenReturn(Mono.just(hotel));
        when(hotelEntityToHotelDTO.map(any(Hotel.class))).thenReturn(hotelDTO);

        StepVerifier.create(hotelService.find("1"))
                .expectNext(hotelDTO)
                .verifyComplete();

        verify(repository).findById("1");
    }

    @Test
    void find_notFound() {
        when(repository.findById(anyString())).thenReturn(Mono.empty());

        StepVerifier.create(hotelService.find("1"))
                .expectError(HotelNotFoundException.class)
                .verify();

        verify(repository).findById("1");
    }

    @Test
    void findAll_success() {
        when(repository.findAll()).thenReturn(Flux.just(hotel));
        when(hotelEntityToHotelDTO.map(any(Hotel.class))).thenReturn(hotelDTO);

        StepVerifier.create(hotelService.findAll())
                .expectNext(hotelDTO)
                .verifyComplete();

        verify(repository).findAll();
    }

    @Test
    void deleteHotel_success() {
        when(repository.deleteById(anyString())).thenReturn(Mono.empty());

        StepVerifier.create(hotelService.deleteHotel("1"))
                .verifyComplete();

        verify(repository).deleteById("1");
    }

    @Test
    void findHotelsByCriteria_success() {
        HotelSearchCriteriaDTO criteria = new HotelSearchCriteriaDTO();
        when(customHotelRepository.searchHotels(any(HotelSearchCriteriaDTO.class))).thenReturn(Flux.just(hotel));
        when(hotelEntityToHotelDTO.map(any(Hotel.class))).thenReturn(hotelDTO);

        StepVerifier.create(hotelService.findHotelsByCriteria(criteria))
                .expectNext(hotelDTO)
                .verifyComplete();

        verify(customHotelRepository).searchHotels(criteria);
    }

    @Test
    void updateHotelAverageRating_success() {
        when(repository.findById(anyString())).thenReturn(Mono.just(hotel));
        when(repository.save(any(Hotel.class))).thenReturn(Mono.just(hotel));
        when(hotelEntityToHotelDTO.map(any(Hotel.class))).thenReturn(hotelDTO);

        hotel.setReviews(Collections.singletonList(new HotelReview(5.0, "Great!", null)));
        StepVerifier.create(hotelService.updateHotelAverageRating("1"))
                .expectNext(hotelDTO)
                .verifyComplete();

        verify(repository, times(2)).findById("1");
        verify(repository).save(hotel);
    }

    @Test
    void calculateAverageRating_success() {
        hotel.setReviews(Collections.singletonList(new HotelReview(5.0, "Great!", null)));
        when(repository.findById(anyString())).thenReturn(Mono.just(hotel));

        StepVerifier.create(hotelService.calculateAverageRating("1"))
                .expectNext(5.0)
                .verifyComplete();

        verify(repository).findById("1");
    }

}