package com.neves.eduardo.desafio.reservationservice.converter;

import com.neves.eduardo.desafio.reservationservice.dto.ReservationDTO;
import com.neves.eduardo.desafio.reservationservice.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationDTOtoReservationEntity {

    Reservation map(ReservationDTO reservationDTO);

}
