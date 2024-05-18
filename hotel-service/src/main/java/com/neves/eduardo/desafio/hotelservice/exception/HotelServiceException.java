package com.neves.eduardo.desafio.hotelservice.exception;

public class HotelServiceException extends RuntimeException {
    public HotelServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
