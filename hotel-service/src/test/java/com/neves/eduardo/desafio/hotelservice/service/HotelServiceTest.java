package com.neves.eduardo.desafio.hotelservice.service;

import com.neves.eduardo.desafio.hotelservice.converter.HotelDTOToHotelEntityConverter;
import com.neves.eduardo.desafio.hotelservice.converter.HotelEntityToHotelDTO;
import com.neves.eduardo.desafio.hotelservice.dto.HotelDTO;
import com.neves.eduardo.desafio.hotelservice.dto.HotelReviewDTO;
import com.neves.eduardo.desafio.hotelservice.dto.HotelSearchCriteriaDTO;
import com.neves.eduardo.desafio.hotelservice.entity.Hotel;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {

    @Mock
    private HotelRepository repository;

    @Mock
    private HotelDTOToHotelEntityConverter hotelDTOtoHotelEntityConverter;

    @Mock
    private HotelEntityToHotelDTO hotelEntityToHotelDTO;

    @Mock
    private CustomHotelRepository customHotelRepository;

    @InjectMocks
    private HotelService hotelService;

    private Hotel hotel;
    private HotelDTO hotelDTO;
    private HotelReviewDTO hotelReviewDTO;
    private HotelSearchCriteriaDTO searchCriteria;

    @BeforeEach
    public void setUp() {
        hotel = new Hotel();
        hotelDTO = new HotelDTO();
        hotelReviewDTO = new HotelReviewDTO();
        searchCriteria = new HotelSearchCriteriaDTO();
    }

    @Test
    public void testCreateHotel() {
        when(hotelDTOtoHotelEntityConverter.map(any(HotelDTO.class))).thenReturn(hotel);
        when(repository.save(any(Hotel.class))).thenReturn(Mono.just(hotel));
        when(hotelEntityToHotelDTO.map(any(Hotel.class))).thenReturn(hotelDTO);

        StepVerifier.create(hotelService.create(hotelDTO))
                .expectNext(hotelDTO)
                .verifyComplete();
    }

    @Test
    public void testFindHotelById() {
        when(repository.findById(anyString())).thenReturn(Mono.just(hotel));
        when(hotelEntityToHotelDTO.map(any(Hotel.class))).thenReturn(hotelDTO);

        StepVerifier.create(hotelService.find("1"))
                .expectNext(hotelDTO)
                .verifyComplete();
    }

    @Test
    public void testFindAllHotels() {
        when(repository.findAll()).thenReturn(Flux.just(hotel));
        when(hotelEntityToHotelDTO.map(any(Hotel.class))).thenReturn(hotelDTO);

        StepVerifier.create(hotelService.findAll())
                .expectNext(hotelDTO)
                .verifyComplete();
    }

    @Test
    public void testDeleteHotel() {
        when(repository.deleteById(anyString())).thenReturn(Mono.empty());

        StepVerifier.create(hotelService.deleteHotel("1"))
                .verifyComplete();
    }

    @Test
    public void testFindHotelsByCriteria() {
        when(customHotelRepository.searchHotels(any(HotelSearchCriteriaDTO.class))).thenReturn(Flux.just(hotel));

        StepVerifier.create(hotelService.findHotelsByCriteria(searchCriteria))
                .expectNext(hotel)
                .verifyComplete();
    }

    @Test
    public void testUpdateHotelAverageRating() {
        when(repository.findById(anyString())).thenReturn(Mono.just(hotel));
        when(repository.save(any(Hotel.class))).thenReturn(Mono.just(hotel));
        when(hotelEntityToHotelDTO.map(any(Hotel.class))).thenReturn(hotelDTO);

        StepVerifier.create(hotelService.updateHotelAverageRating("1"))
                .expectNext(hotelDTO)
                .verifyComplete();
    }

}