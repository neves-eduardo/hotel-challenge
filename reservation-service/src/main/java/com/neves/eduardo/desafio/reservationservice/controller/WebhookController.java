package com.neves.eduardo.desafio.reservationservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebhookController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "reservation-notification";

    @PostMapping("/webhook")
    public String publishMessage(@RequestBody() String message) {
        kafkaTemplate.send(TOPIC, message);
        return "Message published!";
    }

}
