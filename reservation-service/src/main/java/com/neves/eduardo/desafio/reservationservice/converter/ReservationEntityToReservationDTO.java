package com.neves.eduardo.desafio.reservationservice.converter;

import com.neves.eduardo.desafio.reservationservice.dto.ReservationDTO;
import com.neves.eduardo.desafio.reservationservice.entity.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationEntityToReservationDTO {

    ReservationDTO map(Reservation reservation);

}
