package com.neves.eduardo.desafio.reservationservice.consumer;

import com.neves.eduardo.desafio.reservationservice.dto.ReservationWebhookDTO;
import com.neves.eduardo.desafio.reservationservice.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationNotificationConsumer {

    private final ReservationService service;

    @KafkaListener(topics = "reservation-notification", groupId = "my-group")
    public void consume(ReservationWebhookDTO message) {
        service.findById(message.getId())
                .flatMap(reservation -> {
                    reservation.setStatus(message.getStatus());
                    return service.update(reservation);
                })
                .doOnSuccess(updatedReservation -> log.info("Updated reservation: {}", updatedReservation))
                .doOnError(error -> log.error("Failed to update reservation", error))
                .subscribe();
    }

}
