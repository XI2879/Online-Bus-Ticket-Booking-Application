package com.example.BusTicketBookingBackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bus_details")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer busId;

    @NotBlank(message = "Bus name can't be null/blank, Please provide a valid name first!")
    private String busName;

    @NotBlank(message = "Driver name can't be null/blank, Please provide a valid name first!")
    private String driverName;

    @NotBlank(message = "Bus Type can't be null/blank, Please provide a valid bus type")
    private String busType;

    @NotNull(message = "Bus Journey Date can't be null, Please provide correct date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate busJourneyDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime arrivalTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime departureTime;

    @Column(name = "total_seats")
    private Integer seats;

    private Integer availableSeats;


    @ManyToOne(cascade = CascadeType.ALL)
    private Route route;

    @JsonIgnore
    @OneToMany(mappedBy = "bus",cascade = CascadeType.ALL)
    private List<Reservation> reservationList = new ArrayList<>();
}

