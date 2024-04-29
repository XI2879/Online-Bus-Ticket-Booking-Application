package com.example.BusTicketBookingBackend.service;


import com.example.BusTicketBookingBackend.dto.UserDto;
import com.example.BusTicketBookingBackend.entity.User;

public interface UserService {
    UserDto getUserById(Long id);
    UserDto updateUserDetails(Long id, UserDto userDto );

}
