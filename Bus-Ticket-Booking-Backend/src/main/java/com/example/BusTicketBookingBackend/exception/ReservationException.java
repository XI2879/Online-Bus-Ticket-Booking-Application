package com.example.BusTicketBookingBackend.exception;

public class ReservationException extends Exception{
    public ReservationException(){}

    public ReservationException(String message){
        super(message);
    }
}
