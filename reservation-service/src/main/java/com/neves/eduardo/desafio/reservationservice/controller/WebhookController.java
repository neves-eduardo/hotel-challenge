package com.neves.eduardo.desafio.reservationservice.controller;

import com.neves.eduardo.desafio.reservationservice.consumer.ReservationWebhookProducer;
import com.neves.eduardo.desafio.reservationservice.dto.ReservationWebhookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebhookController {

    private final ReservationWebhookProducer producer;

    @PostMapping("/webhook")
    public String publishMessage(@RequestBody ReservationWebhookDTO message) {
        producer.sendMessage(message);
        return "Message published!";
    }

}
