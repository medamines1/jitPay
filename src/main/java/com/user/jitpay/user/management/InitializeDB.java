package com.user.jitpay.user.management;

import com.user.jitpay.user.management.dao.JitPayUser;
import com.user.jitpay.user.management.dao.Location;
import com.user.jitpay.user.management.dao.Locations;
import com.user.jitpay.user.management.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class InitializeDB {


    private final UserRepository userRepository;

    /*
     * init database with a user to test the app
     * */
    @PostConstruct
    public void init() {

        JitPayUser user = new JitPayUser();
        user.setUserId("5a97b952-4ff2-41bf-a194-55aa2a44be17");
        user.setEmail("m_amine22@outlook.com");
        user.setFirstName("Mohamed Amine");
        user.setSecondName("Dahmen");

        user.setLocations(new ArrayList<>());
        // create new location object
        Locations locations = new Locations();
        Location location = new Location();
        location.setLongitude(12324L);
        location.setLatitude(123L);
        locations.setLocation(location);

        user.getLocations().add(locations);
        userRepository.save(user);
    }
}
