package com.neves.eduardo.desafio.reservationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "reservations")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    private String id;
    private String hotelId;
    private String roomId;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private String status;
    private ReservationPayment reservationPayment;

}
