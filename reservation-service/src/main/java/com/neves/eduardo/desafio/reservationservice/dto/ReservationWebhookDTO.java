package com.neves.eduardo.desafio.reservationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReservationWebhookDTO {

    private String id;
    private ReservationStatusDTO status;

}
