package com.user.jitpay.user.management.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {

    private String userId;

    private String email;

    private String firstName;

    private String secondName;

    private List<LocationsDto> locations;
}
