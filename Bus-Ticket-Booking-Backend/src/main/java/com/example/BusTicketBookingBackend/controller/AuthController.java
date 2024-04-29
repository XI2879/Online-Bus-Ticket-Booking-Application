package com.example.BusTicketBookingBackend.controller;


import com.example.BusTicketBookingBackend.dto.JwtAuthResponse;
import com.example.BusTicketBookingBackend.dto.LoginRequestDto;
import com.example.BusTicketBookingBackend.dto.RegisterDto;
import com.example.BusTicketBookingBackend.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("api/bus-ticket/auth")
public class AuthController {
    private AuthService authService;


    @PostMapping("login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginRequestDto requestDto) {
        JwtAuthResponse jwtAuthResponse = authService.login(requestDto);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);

        return ResponseEntity.ok(response);
    }
}
