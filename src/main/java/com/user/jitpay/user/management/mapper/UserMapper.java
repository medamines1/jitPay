package com.user.jitpay.user.management.mapper;

import com.user.jitpay.user.management.dao.JitPayUser;
import com.user.jitpay.user.management.dto.SaveUpdateUser;
import com.user.jitpay.user.management.dto.UserDto;
import com.user.jitpay.user.management.dto.UserLocationsDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;

    public UserDto toUserDto(JitPayUser user) {
        if (user == null) {
            return null;
        }
        return mapper.map(user, UserDto.class);
    }

    public UserLocationsDto toUserLocationDto(JitPayUser user) {

        if (user == null) {
            return null;
        }

        return mapper.map(user, UserLocationsDto.class);
    }


    public JitPayUser toUser(SaveUpdateUser saveUpdateUser) {
        if (saveUpdateUser == null) {
            return null;
        }

        return mapper.map(saveUpdateUser, JitPayUser.class);
    }
}
