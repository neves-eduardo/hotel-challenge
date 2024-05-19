package com.neves.eduardo.desafio.reservationservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReservationPaymentDTO {

    @NotBlank
    private String payerName;

    @NotBlank
    private String payerDocument;

    @NotBlank
    private String payerEmail;

    @NotBlank
    private String payerPhone;

    @NotNull
    private PaymentMethodDTO paymentMethod;

}
