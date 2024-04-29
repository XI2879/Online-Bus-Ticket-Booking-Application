package com.example.BusTicketBookingBackend.service.Impl;


import com.example.BusTicketBookingBackend.dto.JwtAuthResponse;
import com.example.BusTicketBookingBackend.dto.LoginRequestDto;
import com.example.BusTicketBookingBackend.dto.RegisterDto;
import com.example.BusTicketBookingBackend.entity.Role;
import com.example.BusTicketBookingBackend.entity.User;
import com.example.BusTicketBookingBackend.exception.APIException;
import com.example.BusTicketBookingBackend.repository.RoleRepository;
import com.example.BusTicketBookingBackend.repository.UserRepository;
import com.example.BusTicketBookingBackend.security.CustomAuthenticationManager;
import com.example.BusTicketBookingBackend.security.JwtUtil;
import com.example.BusTicketBookingBackend.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private CustomAuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;


    @Override
    public String register(RegisterDto registerDto) {
        // To check whether a user already exists with the same username
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new APIException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
        // To check whether a user already exists with the same email
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new APIException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        // Creating a user from the DTO and to store in the user table
        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);

        user.setRoles(roles);

        userRepository.save(user);
        return "User Registered Successfully!!";
    }

    @Override
    public JwtAuthResponse login(LoginRequestDto loginRequestDto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginRequestDto.getUsername(),
                loginRequestDto.getPassword()
        );
       authenticationManager.authenticate(token);
        String jwt = jwtUtil.generate(loginRequestDto.getUsername());

        Optional<User> userOptional = userRepository.findByUsername(loginRequestDto.getUsername());

        String role = "";
        if (userOptional.isPresent()) {
            User loggedInUser = userOptional.get();
            Optional<Role> optionalRole = loggedInUser.getRoles().stream().findFirst();

            Role userRole = optionalRole.get();
            role = userRole.getName();
        }

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(jwt);
        jwtAuthResponse.setRole(role);

        return jwtAuthResponse;
    }
}
