
package com.example.BusTicketBookingBackend.service.Impl;

import com.example.BusTicketBookingBackend.dto.BusDto;
import com.example.BusTicketBookingBackend.dto.ReservationDto;
import com.example.BusTicketBookingBackend.dto.UserDto;
import com.example.BusTicketBookingBackend.entity.Bus;
import com.example.BusTicketBookingBackend.entity.Reservation;
import com.example.BusTicketBookingBackend.entity.User;
import com.example.BusTicketBookingBackend.exception.ReservationException;
import com.example.BusTicketBookingBackend.repository.BusRepository;
import com.example.BusTicketBookingBackend.repository.ReservationRepository;
import com.example.BusTicketBookingBackend.repository.UserRepository;
import com.example.BusTicketBookingBackend.service.ReservationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private ReservationRepository reservationRepository;
    private BusRepository busRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public ReservationDto addReservation(ReservationDto reservationDto) throws ReservationException {
        Integer busId = (reservationDto.getBus()).getBusId();
        Long userId = (reservationDto.getUser()).getId();
        LocalDate journeyDate = reservationDto.getJourneyDate();

        // Retrieve the bus entity for the given busId and journeyDate
        Bus bus = busRepository.findByIdAndBusJourneyDate(busId, journeyDate)
                .orElseThrow(() -> new ReservationException("Bus not found for the specified date"));

        if (bus.getAvailableSeats() < reservationDto.getBookedSeat()) {
            reservationDto.setStatus("cancelled");
            throw new RuntimeException("Insufficient seats available on the bus");

        } else {
            reservationDto.setStatus("Confirmed");
        }

        // Update available seats on the bus
        bus.setAvailableSeats(bus.getAvailableSeats() - reservationDto.getBookedSeat());
        busRepository.save(bus);
        Reservation reservation = modelMapper.map(reservationDto, Reservation.class);
        Reservation savedReservation = reservationRepository.save(reservation);
        ReservationDto reservationDto1 = modelMapper.map(savedReservation, ReservationDto.class);
        reservationDto1.setBus(modelMapper.map(busRepository.findById(busId).get(), BusDto.class));
        reservationDto1.setUser(modelMapper.map(userRepository.findById(userId).get(),UserDto.class));
        return reservationDto1;
    }

    @Override
    public String deleteReservation(Integer reservationId) throws ReservationException {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationException("Reservations not found"));

        // Retrieve the corresponding bus entity
        Bus bus = reservation.getBus();

        // Update the available seats on the bus
        int bookedSeats = reservation.getBookedSeat();
        bus.setAvailableSeats(bus.getAvailableSeats() + bookedSeats);
        busRepository.save(bus); // Save the updated bus entity

        reservationRepository.delete(reservation);
        return "Reservation Cancelled ";
    }

    @Override
    public List<ReservationDto> getBookingHistoryForUser(Long id) {
        List<Reservation> reservations=reservationRepository.findByUserId(id);


        return reservations.stream()
                .map(reservation->modelMapper.map(reservation,ReservationDto.class))
                .collect(Collectors.toList());
    }


}
