package com.neves.eduardo.desafio.reservationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReservationPayment {

    private String payerName;
    private String payerDocument;
    private String payerEmail;
    private String payerPhone;
    private String paymentMethod;

}
