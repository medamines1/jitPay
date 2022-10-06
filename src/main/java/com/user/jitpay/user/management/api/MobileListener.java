package com.user.jitpay.user.management.api;

import com.user.jitpay.user.management.dto.SavedData;
import com.user.jitpay.user.management.service.RabbitMQSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class MobileListener {

    private final RabbitMQSender rabbitMQSender;

    @PostMapping(value = "/savedData")
    public void producer(@RequestBody SavedData savedData) {

        // validate user
        if (savedData.getUserId() == null) {
            return;
        }

        rabbitMQSender.send(savedData);
    }
}
