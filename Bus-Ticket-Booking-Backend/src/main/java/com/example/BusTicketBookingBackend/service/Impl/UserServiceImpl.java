package com.example.BusTicketBookingBackend.service.Impl;

import com.example.BusTicketBookingBackend.dto.UserDto;
import com.example.BusTicketBookingBackend.entity.User;
import com.example.BusTicketBookingBackend.repository.UserRepository;
import com.example.BusTicketBookingBackend.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.getById(id);
//        UserDto userDto = modelMapper.map(user, UserDto.class);
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(passwordEncoder.encode(user.getPassword()));

        return userDto;
    }

    @Override
    public UserDto updateUserDetails(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(user);

        return modelMapper.map(user, UserDto.class);
    }

}
