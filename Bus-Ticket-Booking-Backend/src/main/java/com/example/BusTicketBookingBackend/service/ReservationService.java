package com.example.BusTicketBookingBackend.service;

import com.example.BusTicketBookingBackend.dto.ReservationDto;
import com.example.BusTicketBookingBackend.entity.Reservation;
import com.example.BusTicketBookingBackend.exception.ReservationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationService {

    public ReservationDto addReservation(ReservationDto reservationDto) throws  ReservationException;
    public String deleteReservation(Integer rid) throws ReservationException;
    public List<ReservationDto> getBookingHistoryForUser(Long id);

//    public Reservation viewReservation(Integer rid, String key) throws ReservationException;
//
//    public List<Reservation> getAllReservation(String key) throws ReservationException;
//
//    public List<Reservation> viewReservationByUserId(Long uid, String key) throws ReservationException;
//

//
//    public Reservation updateReservation(Integer rid, ReservationDto reservationDto, String key) throws ReservationException;

}
