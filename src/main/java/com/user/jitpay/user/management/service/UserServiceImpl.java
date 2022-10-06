package com.user.jitpay.user.management.service;

import com.user.jitpay.user.management.dao.JitPayUser;
import com.user.jitpay.user.management.dto.SaveUpdateUser;
import com.user.jitpay.user.management.dto.UserDto;
import com.user.jitpay.user.management.dto.UserLocationsDto;
import com.user.jitpay.user.management.mapper.UserMapper;
import com.user.jitpay.user.management.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    /*
     * add or update specific fields for the user
     *
     * @param SaveUpdateUser
     * */
    @Override
    public void saveUpdateUser(SaveUpdateUser saveUpdateUser) {
        JitPayUser user = this.userRepository.findById(saveUpdateUser.getUserId()).orElse(userMapper.toUser(saveUpdateUser));

        if (saveUpdateUser.getEmail() != null && !saveUpdateUser.getEmail().isEmpty()) {
            user.setEmail(saveUpdateUser.getEmail());
        }

        if (saveUpdateUser.getFirstName() != null && !saveUpdateUser.getFirstName().isEmpty()) {
            user.setFirstName(saveUpdateUser.getFirstName());
        }


        if (saveUpdateUser.getSecondName() != null && !saveUpdateUser.getSecondName().isEmpty()) {
            user.setSecondName(saveUpdateUser.getSecondName());
        }


        this.userRepository.save(user);
    }

    @Override
    public UserDto getUserData(String userId) {
        return this.userMapper.toUserDto(this.userRepository.findById(userId).orElse(null));
    }

    /*
     * search user location by creation date in specific range
     *
     * @param userId
     * @param startDate
     * @param endDate
     * */
    @SneakyThrows
    @Override
    public UserLocationsDto getUserData(String userId, String startDate, String endDate) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Date stDate = format.parse(startDate);
        Date eDate = format.parse(endDate);

        return this.userMapper.toUserLocationDto(this.userRepository.findByLocationsInRange(userId, stDate, eDate));
    }
}
