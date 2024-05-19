package com.neves.eduardo.desafio.reservationservice.dto;

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
    private String hotelId;
    private String roomId;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private ReservationStatusDTO status;
    private ReservationPaymentDTO reservationPayment;

}
