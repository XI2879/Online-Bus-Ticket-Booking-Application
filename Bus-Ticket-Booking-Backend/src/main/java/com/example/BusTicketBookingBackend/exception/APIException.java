package com.example.BusTicketBookingBackend.exception;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@AllArgsConstructor
public class APIException extends RuntimeException{
    private HttpStatus status;
    private String message;
}
