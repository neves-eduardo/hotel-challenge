package com.neves.eduardo.desafio.reservationservice.exception;

public class RoomNotAvailableException extends RuntimeException {

    public RoomNotAvailableException(String message) {
        super(message);
    }

}
