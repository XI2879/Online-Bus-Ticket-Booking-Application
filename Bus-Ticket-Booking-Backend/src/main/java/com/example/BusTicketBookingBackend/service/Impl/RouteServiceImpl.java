package com.example.BusTicketBookingBackend.service.Impl;


import com.example.BusTicketBookingBackend.dto.BusDto;
import com.example.BusTicketBookingBackend.entity.Bus;
import com.example.BusTicketBookingBackend.entity.Route;
import com.example.BusTicketBookingBackend.exception.RouteException;
import com.example.BusTicketBookingBackend.repository.RouteRepository;
import com.example.BusTicketBookingBackend.service.RouteService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RouteServiceImpl implements RouteService {
	private RouteRepository routerepository;
	private ModelMapper modelMapper;

	@Override
	public List<BusDto> getBusesByRouteFromAndRouteTo(String routeFrom, String routeTo, LocalDate busJourneyDate) throws RouteException {
		if (routeFrom == null || routeTo == null || routeFrom.isEmpty() || routeTo.isEmpty()) {
			throw new RouteException("RouteFrom and RouteTo parameters are required.");
		}
		List<Bus> buses = routerepository.findBusesByRouteFromAndRouteTo(routeFrom,routeTo);
		if (buses.isEmpty()) {
			throw new RouteException("No buses found for the specified route.");
		}

		return buses.stream().filter(bus->bus.getBusJourneyDate().equals(busJourneyDate)).map(bus -> modelMapper.map(bus, BusDto.class)).collect(Collectors.toList());
	}
}
