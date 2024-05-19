package com.neves.eduardo.desafio.reservationservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    private String id;

    @NotNull
    private String hotelId;

    @NotNull
    private String roomId;

    @NotNull
    private LocalDateTime checkInDate;

    @NotNull
    private LocalDateTime checkOutDate;

    @NotNull
    private ReservationStatusDTO status;

    @NotNull
    private ReservationPaymentDTO reservationPayment;

}
