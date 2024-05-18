package com.neves.eduardo.desafio.hotelservice.service;

import com.neves.eduardo.desafio.hotelservice.converter.HotelDTOToHotelEntityConverter;
import com.neves.eduardo.desafio.hotelservice.converter.HotelEntityToHotelDTO;
import com.neves.eduardo.desafio.hotelservice.dto.HotelDTO;
import com.neves.eduardo.desafio.hotelservice.dto.HotelReviewDTO;
import com.neves.eduardo.desafio.hotelservice.dto.HotelSearchCriteriaDTO;
import com.neves.eduardo.desafio.hotelservice.entity.Hotel;
import com.neves.eduardo.desafio.hotelservice.entity.HotelReview;
import com.neves.eduardo.desafio.hotelservice.repository.CustomHotelRepository;
import com.neves.eduardo.desafio.hotelservice.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository repository;
    private final HotelDTOToHotelEntityConverter hotelDTOtoHotelEntityConverter;
    private final HotelEntityToHotelDTO hotelEntityToHotelDTO;
    private final CustomHotelRepository customHotelRepository;

    public Mono<HotelDTO> create(HotelDTO hotelDTO) {
        Hotel hotelEntity = hotelDTOtoHotelEntityConverter.map(hotelDTO);
        return repository.save(hotelEntity).map(hotelEntityToHotelDTO::map);
    }

    public Mono<HotelDTO> find(String id) {
        return repository.findById(id).map(hotelEntityToHotelDTO::map);
    }

    public Flux<HotelDTO> findAll() {
        return repository.findAll().map(hotelEntityToHotelDTO::map);
    }

    public Mono<Void> deleteHotel(String id) {
        return repository.deleteById(id);
    }

    public Flux<Hotel> findHotelsByCriteria(HotelSearchCriteriaDTO criteria) {
        return customHotelRepository.searchHotels(criteria);
    }

    public Mono<HotelDTO> updateHotelAverageRating(String id) {
        return calculateAverageRating(id).flatMap(averageRating ->
                repository.findById(id).flatMap(hotel -> {
                    hotel.setAverageRating(averageRating);
                    return repository.save(hotel);})
        ).map(hotelEntityToHotelDTO::map);
    }

    public Mono<HotelDTO> createHotelRating(String id, HotelReviewDTO reviewDTO) {
        return repository.findById(id).flatMap(hotel -> {
                    List<HotelReview> reviews = hotel.getReviews();

                    HotelReview review = HotelReview.builder()
                            .stars(reviewDTO.getStars())
                            .message(reviewDTO.getMessage())
                            .createdAt(reviewDTO.getCreatedAt())
                            .build();

                    reviews.add(review);
                    return repository.save(hotel.toBuilder()
                            .reviews(reviews)
                            .build());
                })
                .flatMap(hotel -> updateHotelAverageRating(hotel.getId()));
    }

    private Mono<Double> calculateAverageRating(String id) {
        return repository.findById(id).flatMap(hotel -> {
                    List<HotelReview> reviews = hotel.getReviews();
                    if (isEmpty(reviews)) {
                        return Mono.just(0.0);
                    }
                    double sum = reviews.stream().mapToDouble(HotelReview::getStars).sum();
                    double average = sum / reviews.size();
                    return Mono.just(average);
        });
    }

}
