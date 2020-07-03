package com.example.demo.space;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MarsColonyMessageListener {
    @RabbitListener(queues = "marscommunication")
    public void onMessage(String message) {
        log.info("marscommunication " + message);
        throw new RuntimeException("on purpose...");
    }



}
