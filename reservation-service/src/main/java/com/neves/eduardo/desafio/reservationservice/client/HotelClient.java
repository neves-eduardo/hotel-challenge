package com.neves.eduardo.desafio.reservationservice.client;

import com.neves.eduardo.desafio.reservationservice.dto.hotel.HotelDTO;
import com.neves.eduardo.desafio.reservationservice.dto.hotel.HotelSearchCriteriaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class HotelClient {

    private final WebClient webClient;

    public Mono<HotelDTO> getHotelById(String id) {
        return webClient.get()
                .uri("/hotels/{id}", id)
                .retrieve()
                .bodyToMono(HotelDTO.class);
    }

    public Flux<HotelDTO> searchHotels(HotelSearchCriteriaDTO criteria) {
        return webClient.post()
                .uri("/hotels/search")
                .body(Mono.just(criteria), HotelSearchCriteriaDTO.class)
                .retrieve()
                .bodyToFlux(HotelDTO.class);
    }

}
