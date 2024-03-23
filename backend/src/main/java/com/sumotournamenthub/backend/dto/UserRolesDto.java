package com.sumotournamenthub.backend.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserRolesDto
{
    boolean admin;
    boolean staffCoach;
    boolean coach;
}
