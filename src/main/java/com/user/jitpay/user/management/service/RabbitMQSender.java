package com.user.jitpay.user.management.service;


import com.user.jitpay.user.management.config.RabbitMQConfiguration;
import com.user.jitpay.user.management.dto.SavedData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQSender {


    private final AmqpTemplate rabbitTemplate;

    private final RabbitMQConfiguration rabbitMQConfiguration;


    public void send(SavedData savedData) {
        rabbitTemplate.convertAndSend(rabbitMQConfiguration.getExchange(), rabbitMQConfiguration.getRoutingkey(), savedData);
        log.info("Send data = {}", savedData);

    }
}
