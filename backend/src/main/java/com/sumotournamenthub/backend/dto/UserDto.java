package com.sumotournamenthub.backend.dto;

import com.sumotournamenthub.backend.constants.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
    int id;
    String email;
    String firstName;
    String lastName;
    String password;
    Role role;
}
