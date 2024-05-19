package com.neves.eduardo.desafio.reservationservice.consumer;

import com.neves.eduardo.desafio.reservationservice.dto.ReservationWebhookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationWebhookProducer {

    private final KafkaTemplate<String, ReservationWebhookDTO> kafkaTemplate;

    private static final String TOPIC = "reservation-notification";

    public void sendMessage(ReservationWebhookDTO message) {
        kafkaTemplate.send(TOPIC, message);
    }

}
