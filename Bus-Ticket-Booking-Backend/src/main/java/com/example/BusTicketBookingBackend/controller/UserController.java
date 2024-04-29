package com.example.BusTicketBookingBackend.controller;

import com.example.BusTicketBookingBackend.dto.UserDto;
import com.example.BusTicketBookingBackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> viewUserDetails(@PathVariable Long id){
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUserDetails(@PathVariable Long id,@RequestBody UserDto dto){
        UserDto userDto = userService.updateUserDetails(id,dto);
        return ResponseEntity.ok(userDto);
    }

}
