package com.example.demo;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

import java.sql.Connection;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    @Bean
    RabbitAdmin admin(RabbitTemplate rabbitTemplate){
        return new RabbitAdmin(rabbitTemplate);
    }

//    @Bean
//    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
//        RabbitTemplate rt = new RabbitTemplate(connectionFactory);
//        ExponentialBackOffPolicy policy = new ExponentialBackOffPolicy();
//        policy.setInitialInterval(2000);
//        policy.setMaxInterval(60000);
//        policy.setMultiplier(3);
//        RetryTemplate retryTemplate = new RetryTemplate();
//        retryTemplate.setBackOffPolicy(policy);
//        rt.setRetryTemplate(retryTemplate);
//        return rt;
//    }

}
