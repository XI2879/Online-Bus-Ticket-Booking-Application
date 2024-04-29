package com.example.BusTicketBookingBackend.controller;


import com.example.BusTicketBookingBackend.dto.BusDto;
import com.example.BusTicketBookingBackend.entity.Bus;
import com.example.BusTicketBookingBackend.exception.BusException;
import com.example.BusTicketBookingBackend.service.BusService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/buses")
@AllArgsConstructor
public class BusController {
    private BusService busService;
    //admin endpoints


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin/add")
    public ResponseEntity<BusDto> addBus(@Valid @RequestBody BusDto busDto)throws BusException  {

        BusDto newBus = busService.addBus(busDto);
        return new ResponseEntity<>(newBus, HttpStatus.CREATED);


    }


}

// format for sending post request
//{
//        "busName": "Express Shuttle",
//        "driverName": "John Doe",
//        "busType": "Sleeper",
//        "busJourneyDate": "2024-05-01",
//        "arrivalTime": "08:00:00",
//        "departureTime": "09:00:00",
//        "seats": 50,
//        "availableSeats": 50,
//        "route": {
//        "routeFrom": "City A",
//        "routeTo": "City B",
//        "distance": 200
//        }
//        }