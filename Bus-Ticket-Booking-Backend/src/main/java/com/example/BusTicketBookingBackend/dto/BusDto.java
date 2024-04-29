package com.example.BusTicketBookingBackend.dto;

import com.example.BusTicketBookingBackend.entity.Route;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusDto {
    private Integer busId;
    private String busName;
    private String driverName;
    private String busType;
    private LocalDate busJourneyDate;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private Integer seats;
    private Integer availableSeats;
    private Route route;

}
