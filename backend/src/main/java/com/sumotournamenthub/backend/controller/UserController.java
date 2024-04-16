package com.sumotournamenthub.backend.controller;

import com.sumotournamenthub.backend.domain.User;
import com.sumotournamenthub.backend.dto.UserDto;
import com.sumotournamenthub.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/add_user")
    public User createUser(@RequestBody UserDto userDto) {
        return userService.saveOrUpdateUser(userDto);

    }

}