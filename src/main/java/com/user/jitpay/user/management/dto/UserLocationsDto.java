package com.user.jitpay.user.management.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserLocationsDto {

    private String userId;

    private List<LocationsDto> locations;
}
