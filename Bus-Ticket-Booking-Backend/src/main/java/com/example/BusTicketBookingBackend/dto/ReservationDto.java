package com.example.BusTicketBookingBackend.dto;

import com.example.BusTicketBookingBackend.entity.Bus;
import com.example.BusTicketBookingBackend.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
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
public class ReservationDto {
    private Integer reservationId;
    @NotNull(message = "Source required to book a reservation")
    @NotBlank(message = "Source should not be blanked")
    private String source;

    @NotNull(message = "Destination required to book a reservation")
    @NotBlank(message = "Destination should not be blanked")
    private String destination;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate journeyDate;
    private Integer reservationID;
    private String status;
    private LocalDate date;
    private LocalTime time;
    private Integer fare;
//    private Integer busId;
//    private Long userId;
    @Min(1)
    private Integer bookedSeat;
    private UserDto user;
    private BusDto bus;
}
