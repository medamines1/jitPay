package com.user.jitpay.user.management.service;

import com.user.jitpay.user.management.dao.Location;
import com.user.jitpay.user.management.dao.Locations;
import com.user.jitpay.user.management.dao.JitPayUser;
import com.user.jitpay.user.management.dto.SavedData;
import com.user.jitpay.user.management.mapper.UserMapper;
import com.user.jitpay.user.management.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RabbitListener(queues = "userLocations", id = "listener")
@RequiredArgsConstructor
@Slf4j
public class RabbitMQReceiver {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    /*
     * Consumer to save to mobile application locations to database
     *
     * @RequestParam SavedData
     * @return void
     * */
    @RabbitHandler
    public void receiver(SavedData savedData) {
        log.info("SavedData listener invoked - Consuming Message with user Identifier : " + savedData.getUserId());

        // retrieve user
        JitPayUser user = userRepository.findById(savedData.getUserId()).orElse(null);


        // user was not found
        if (user == null) {
            return;
        }

        // create new location object
        Locations locations = new Locations();
        Location location = new Location();
        location.setLongitude(savedData.getLocationDto().getLongitude());
        location.setLatitude(savedData.getLocationDto().getLatitude());
        locations.setLocation(location);

        //add location and save to database
        if (user.getLocations() == null) {
            user.setLocations(new ArrayList<>());
        }
        user.getLocations().add(locations);

        userRepository.save(user);

    }
}
