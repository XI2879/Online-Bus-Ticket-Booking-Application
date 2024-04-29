package com.example.BusTicketBookingBackend.service;



import com.example.BusTicketBookingBackend.dto.BusDto;
import com.example.BusTicketBookingBackend.entity.Bus;
import com.example.BusTicketBookingBackend.exception.BusException;

import java.util.List;

public interface BusService {
    //admin methods
    public BusDto addBus(BusDto busDto) throws BusException ;

//    public Bus updateBus(Bus bus, String key) throws BusException;
//    public Bus deleteBus(Integer busId, String key) throws BusException, BusException;
//
//    //admin + user methods
//    public Bus viewBus(Integer busId) throws BusException;
//    public List<Bus> viewBusByBusType(String busType) throws BusException;
//    public List<Bus> viewAllBuses() throws BusException;
}
