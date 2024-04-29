package com.example.BusTicketBookingBackend.service;


import com.example.BusTicketBookingBackend.dto.JwtAuthResponse;
import com.example.BusTicketBookingBackend.dto.LoginRequestDto;
import com.example.BusTicketBookingBackend.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);

    JwtAuthResponse login(LoginRequestDto loginRequestDto);
}
