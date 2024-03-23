package com.sumotournamenthub.backend.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto
{
    int id;
    String email;
    String firstName;
    String lastName;
    UserRolesDto roles;
    int clubId;
}
