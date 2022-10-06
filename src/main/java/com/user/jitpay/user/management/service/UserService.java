package com.user.jitpay.user.management.service;

import com.user.jitpay.user.management.dto.SaveUpdateUser;
import com.user.jitpay.user.management.dto.UserDto;
import com.user.jitpay.user.management.dto.UserLocationsDto;

import java.util.Date;

public interface UserService {

    void saveUpdateUser(SaveUpdateUser saveUpdateUser);

    UserDto getUserData(String userId);

    UserLocationsDto getUserData(String userId, String startDate, String endDate);
}
