package com.example.BusTicketBookingBackend.service;



import com.example.BusTicketBookingBackend.dto.BusDto;
import com.example.BusTicketBookingBackend.entity.Route;
import com.example.BusTicketBookingBackend.exception.RouteException;

import java.time.LocalDate;
import java.util.List;

public interface RouteService {
//	public Route addRoute(Route route, String key) throws RouteException;
//	public List<Route> viewAllRoute() throws RouteException;
//	public Route viewRoute(int routeId) throws RouteException;
//	public Route updateRoute(Route route,String key) throws RouteException;
//	public Route deleteRoute(int routeID,String key) throws RouteException;
	public List<BusDto> getBusesByRouteFromAndRouteTo(String routeFrom, String routeTo, LocalDate busJourneyDate) throws RouteException;
	
	
}
