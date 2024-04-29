package com.example.BusTicketBookingBackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservation_details")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationID;
    private String status;
    private LocalDate date;
    private LocalTime time;
    private String source;
    private String destination;
    private LocalDate journeyDate;
    private Integer bookedSeat;
    private Integer fare;
    @ManyToOne
    private User user;
    @ManyToOne
    private Bus bus;
}
