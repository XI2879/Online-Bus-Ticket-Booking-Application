package com.example.BusTicketBookingBackend.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusException.class)
    public ResponseEntity<ErrorDetails> busExceptionHandler(BusException busException, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                busException.getMessage(),
                webRequest.getDescription(false),
                "BUS_NOT_FOUND");
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RouteException.class)
    public ResponseEntity<ErrorDetails> routeExceptionHandler(RouteException routeException,WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(
        LocalDateTime.now(),
        routeException.getMessage(),
        webRequest.getDescription(false),
        "ROUTE_NOT_FOUND");
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ReservationException.class)
    public ResponseEntity<ErrorDetails> reservationExceptionHandler(ReservationException reservationException, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                reservationException.getMessage(),
                webRequest.getDescription(false),
                "RESERVATIONS_NOT_FOUND");
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleEmailAlreadyExistsException (EmailAlreadyExistsException exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "EMPLOYEE_EMAIL_ALREADY_EXISTS"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        List<ObjectError> objectErrors =  ex.getBindingResult().getAllErrors();
        objectErrors.forEach(objectError ->
                errors.put(((FieldError)objectError).getField(),objectError.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(NewBadCredentialsException.class)
    public ResponseEntity<ErrorDetails> handleBadCredentialsException(NewBadCredentialsException exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "INVALID CREDENTIALS"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException (Exception exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "INTERNAL_SERVER_ERROR"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
