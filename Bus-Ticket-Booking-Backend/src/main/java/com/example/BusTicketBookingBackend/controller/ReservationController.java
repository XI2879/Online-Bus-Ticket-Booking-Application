package com.example.BusTicketBookingBackend.controller;



import com.example.BusTicketBookingBackend.dto.ReservationDto;
import com.example.BusTicketBookingBackend.entity.Reservation;
import com.example.BusTicketBookingBackend.exception.ReservationException;
import com.example.BusTicketBookingBackend.service.ReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/buses/reservation")
@AllArgsConstructor
public class ReservationController {
    private ReservationService reservationService;
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/add")
    public ResponseEntity<ReservationDto> addReservation(@Valid @RequestBody ReservationDto reservationDto) throws ReservationException {


        ReservationDto reservation = reservationService.addReservation(reservationDto);

        return  new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/delete/{rid}")
    public String deleteReservation( @PathVariable Integer rid) throws ReservationException{
        return reservationService.deleteReservation(rid);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/list/{id}")
    public ResponseEntity<List<ReservationDto>> getReservationsByUser( @PathVariable Long id) throws ReservationException{
        List<ReservationDto> reservationDtoList = reservationService.getBookingHistoryForUser(id);
        return ResponseEntity.ok(reservationDtoList);
    }

}
