package com.example.BusTicketBookingBackend.controller;


import com.example.BusTicketBookingBackend.dto.BusDto;
import com.example.BusTicketBookingBackend.entity.Route;
import com.example.BusTicketBookingBackend.exception.RouteException;
import com.example.BusTicketBookingBackend.service.RouteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/routes")
@AllArgsConstructor
public class RouteController {
	private RouteService routeService;

	///http://localhost:8080/api/routes/buses?routeFrom=City A&routeTo=City B
//	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/buses")
	public ResponseEntity<List<BusDto>> getBusesByRoute(
			@RequestParam String routeFrom,
			@RequestParam String routeTo,
			@RequestParam LocalDate busJourneyDate) throws RouteException {
		List<BusDto> buses = routeService.getBusesByRouteFromAndRouteTo(routeFrom,routeTo,busJourneyDate);
		return new ResponseEntity<>(buses, HttpStatus.OK);
	}
	
}
