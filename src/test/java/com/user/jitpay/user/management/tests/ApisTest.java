package com.user.jitpay.user.management.tests;

import com.user.jitpay.user.management.dao.JitPayUser;
import com.user.jitpay.user.management.dto.LocationDto;
import com.user.jitpay.user.management.dto.SaveUpdateUser;
import com.user.jitpay.user.management.dto.SavedData;
import com.user.jitpay.user.management.dto.UserLocationsDto;
import com.user.jitpay.user.management.repo.UserRepository;
import com.user.jitpay.user.management.service.RabbitMQReceiver;
import com.user.jitpay.user.management.service.UserService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@NoArgsConstructor
@ActiveProfiles("test")
public class ApisTest {

    private static String USER_ID = "5a97b952-4ff2-41bf-a194-55aa2a44be17";

    @Autowired
    private RabbitTemplate queueSender;

    @Autowired
    private RabbitMQReceiver rabbitMQReceiver;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    /*
     * this will test the rabbitMq receiver and sender
     * No need to add setup as the post construct "InitializeDB" will add the user
     */
    @Test
    public void shouldSaveUser() {

        LocationDto locationDto = new LocationDto();
        locationDto.setLatitude(123434L);
        locationDto.setLongitude(5533L);

        SavedData savedData = new SavedData();
        savedData.setUserId(USER_ID);
        savedData.setLocationDto(locationDto);
        queueSender.convertAndSend("teste-exchange", "routing-key-teste", savedData);

        rabbitMQReceiver.receiver(savedData);

        JitPayUser newUser = userRepository.findById(USER_ID).orElse(null);
        assert newUser != null;
        Assert.assertEquals(newUser.getLocations().size(), 2);
    }

    @Test
    public void shouldSaveAndUpdateUser() {

        SaveUpdateUser saveUpdateUser = new SaveUpdateUser();
        saveUpdateUser.setUserId(USER_ID);
        saveUpdateUser.setEmail("m_amine34@yopmail.com");
        saveUpdateUser.setFirstName("new First name");
        saveUpdateUser.setSecondName("new second name");

        userService.saveUpdateUser(saveUpdateUser);

        JitPayUser newUser = userRepository.findById(USER_ID).orElse(null);
        assert newUser != null;
        Assert.assertEquals(newUser.getEmail(), "m_amine34@yopmail.com");
        Assert.assertEquals(newUser.getFirstName(), "new First name");
        Assert.assertEquals(newUser.getSecondName(), "new second name");

    }

    @Test
    public void shouldGetUserLocationsWithRange() {

        String startDate = "2022-9-08T11:44:00.524";
        String endDate = "2022-11-08T11:44:00.524";

        UserLocationsDto userLocationsDto = userService.getUserData(USER_ID, startDate, endDate);

        Assert.assertNotEquals(userLocationsDto, null);
    }

}
