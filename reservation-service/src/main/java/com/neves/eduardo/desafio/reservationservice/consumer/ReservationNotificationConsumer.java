package com.neves.eduardo.desafio.reservationservice.consumer;

import com.neves.eduardo.desafio.reservationservice.dto.ReservationWebhookDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ReservationNotificationConsumer {

    @KafkaListener(topics = "reservation-notification", groupId = "my-group")
    public void consume(ReservationWebhookDTO message) {
        System.out.println("Message received: " + message);
    }

}
