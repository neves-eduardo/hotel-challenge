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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelService {

    private final HotelRepository repository;
    private final HotelDTOToHotelEntityConverter hotelDTOtoHotelEntityConverter;
    private final HotelEntityToHotelDTO hotelEntityToHotelDTO;
    private final CustomHotelRepository customHotelRepository;

    public Mono<HotelDTO> create(HotelDTO hotelDTO) {
        log.info("[hotel-service] [service] Creating a new hotel.");
        Hotel hotelEntity = hotelDTOtoHotelEntityConverter.map(hotelDTO);

        return repository.save(hotelEntity)
                .map(hotelEntityToHotelDTO::map)
                .doOnSuccess(savedHotel -> log.info("[hotel-service] [service] Hotel created successfully."))
                .doOnError(error -> log.error("[hotel-service] [service] Error creating hotel.", error));
    }

    public Mono<HotelDTO> find(String id) {
        log.info("[hotel-service] [service] Finding hotel by id [{}].", id);

        return repository.findById(id)
                .map(hotelEntityToHotelDTO::map)
                .switchIfEmpty(Mono.error(new RuntimeException("Hotel not found")))
                .doOnSuccess(hotel -> log.info("[hotel-service] [service] Hotel found with id [{}].", id))
                .doOnError(error -> log.error("[hotel-service] [service] Error finding hotel by id [{}].", id, error));

    }

    public Flux<HotelDTO> findAll() {
        log.info("[hotel-service] [service] Retrieving all hotels.");

        return repository.findAll()
                .map(hotelEntityToHotelDTO::map)
                .doOnComplete(() -> log.info("[hotel-service] [service] All hotels retrieved."))
                .doOnError(error -> log.error("[hotel-service] [service] Error retrieving all hotels.", error));

    }

    public Mono<Void> deleteHotel(String id) {
        log.info("[hotel-service] [service] Deleting hotel with id [{}].", id);
        return repository.deleteById(id)
                .doOnSuccess(v -> log.info("[hotel-service] [service] Hotel deleted with id [{}].", id))
                .doOnError(error -> log.error("[hotel-service] [service] Error deleting hotel with id [{}].", id, error));

    }

    public Flux<HotelDTO> findHotelsByCriteria(HotelSearchCriteriaDTO criteria) {
        log.info(String.format("[hotel-service] [service] searching hotels with criteria [%s].", criteria));
        return customHotelRepository.searchHotels(criteria)
                .map(hotelEntityToHotelDTO::map)
                .doOnComplete(() -> log.info("[hotel-service] [service] Search completed."))
                .doOnError(error -> log.error("[hotel-service] [service] Error searching hotels with criteria [{}].", criteria, error));

    }

    public Mono<HotelDTO> updateHotelAverageRating(String id) {
        log.info(String.format("[hotel-service] [service] updating hotel with id [%s] average rating.", id));

        return calculateAverageRating(id).flatMap(averageRating ->
                repository.findById(id).flatMap(hotel -> {
                    hotel.setAverageRating(averageRating);
                    return repository.save(hotel);
                })
        ).map(hotelEntityToHotelDTO::map)
                    .doOnSuccess(updatedHotel -> log.info("[hotel-service] [service] Updated average rating for hotel with id [{}].", id))
                    .doOnError(error -> log.error("[hotel-service] [service] Error updating average rating for hotel with id [{}].", id, error));

    }

    public Mono<HotelDTO> createHotelRating(String id, HotelReviewDTO reviewDTO) {
        log.info(String.format("[hotel-service] [service] creating hotel rating (body: [%s]) for hotel with id [%s].", reviewDTO, id));

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
