package com.example.BusTicketBookingBackend.service.Impl;

import com.example.BusTicketBookingBackend.dto.BusDto;
import com.example.BusTicketBookingBackend.dto.RouteDto;
import com.example.BusTicketBookingBackend.entity.Bus;
import com.example.BusTicketBookingBackend.entity.Route;
import com.example.BusTicketBookingBackend.exception.BusException;
import com.example.BusTicketBookingBackend.repository.BusRepository;
import com.example.BusTicketBookingBackend.repository.RouteRepository;
import com.example.BusTicketBookingBackend.service.BusService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BusServiceImpl implements BusService {
    private BusRepository busRepository;
    private RouteRepository routeRepository;
    private ModelMapper modelMapper;

    //admin access methods
    @Override
    public BusDto addBus(BusDto busDto)  {

        Bus bus = modelMapper.map(busDto, Bus.class);
        RouteDto routeDto = modelMapper.map(busDto.getRoute(), RouteDto.class);

        // Retrieve or create the route based on routeFrom and routeTo
        Route route = routeRepository.findByRouteFromAndRouteTo(routeDto.getRouteFrom(), routeDto.getRouteTo());
        if (route == null) {
            route = new Route(routeDto.getRouteFrom(), routeDto.getRouteTo(), routeDto.getDistance());
            route = routeRepository.save(route);
        }
        bus.setRoute(route);
        Bus savedBus = busRepository.save(bus);

        return modelMapper.map(savedBus, BusDto.class);
    }

}
