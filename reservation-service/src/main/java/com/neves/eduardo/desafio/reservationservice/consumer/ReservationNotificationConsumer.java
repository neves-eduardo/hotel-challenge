package com.neves.eduardo.desafio.reservationservice.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ReservationNotificationConsumer {

    @KafkaListener(topics = "reservation-notification", groupId = "my-group")
    public void consume(String message) {
        System.out.println("Message received: " + message);
    }

}
