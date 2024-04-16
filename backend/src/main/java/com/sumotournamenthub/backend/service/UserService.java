package com.sumotournamenthub.backend.service;

import com.sumotournamenthub.backend.domain.User;
import com.sumotournamenthub.backend.dto.UserDto;
import com.sumotournamenthub.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder =passwordEncoder;
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public User saveOrUpdateUser(UserDto userDto) {
        return userRepository.findByEmail(userDto.getEmail()).
                orElse(userRepository.save(convertToEntity(userDto)));
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(UserService::convertToDto).collect(Collectors.toList());
    }

    public static UserDto convertToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    public User convertToEntity(UserDto userDto) {
        String encodedPassword = encodePassword(userDto.getPassword());
        return new User(userDto.getEmail(), userDto.getFirstName(), userDto.getLastName(), encodedPassword, userDto.getRole());
    }

}
