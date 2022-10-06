package com.user.jitpay.user.management.api;

import com.user.jitpay.user.management.dto.SaveUpdateUser;
import com.user.jitpay.user.management.dto.UserDto;
import com.user.jitpay.user.management.dto.UserLocationsDto;
import com.user.jitpay.user.management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user-data")
@RequiredArgsConstructor
public class WebAppApis {

    private final UserService userService;

    @PostMapping("/save-update-data")
    public HttpEntity<String> saveData(@RequestBody SaveUpdateUser saveUpdateUser) {

        this.userService.saveUpdateUser(saveUpdateUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public HttpEntity<UserDto> getUserData(@RequestParam String userId) {

        return new ResponseEntity<>(this.userService.getUserData(userId), HttpStatus.OK);
    }

    @GetMapping("user-locations")
    public HttpEntity<UserLocationsDto> userLocations(@RequestParam String userId,@RequestParam String startDate, @RequestParam String endDate) {

        return new ResponseEntity<>(this.userService.getUserData(userId,startDate, endDate), HttpStatus.OK);
    }
}
