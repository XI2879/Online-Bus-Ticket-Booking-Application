package com.example.BusTicketBookingBackend.repository;


import com.example.BusTicketBookingBackend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByUserId(Long id);
}
