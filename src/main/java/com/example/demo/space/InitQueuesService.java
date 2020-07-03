package com.example.demo.space;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class InitQueuesService {
    private final RabbitAdmin rabbitAdmin;

    @PostConstruct
    public void create(){
        rabbitAdmin.deleteQueue("mars-dlq");
        rabbitAdmin.deleteQueue("marscommunication");
        rabbitAdmin.deleteExchange("mars-dlq-exchange");
        rabbitAdmin.deleteExchange("marsexchange");


        Queue queue = QueueBuilder.nonDurable("marscommunication")
                .deadLetterExchange("mars-dlq-exchange")
                .build();
        Exchange exchange = ExchangeBuilder.directExchange("marsexchange").build();
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareExchange(exchange);
        rabbitAdmin.declareBinding(new Binding("marscommunication", Binding.DestinationType.QUEUE,
                "marsexchange", "",null));

        rabbitAdmin.declareQueue(QueueBuilder.nonDurable("mars-dlq").build());
        rabbitAdmin.declareExchange(ExchangeBuilder.directExchange("mars-dlq-exchange").build());
        rabbitAdmin.declareBinding(new Binding("mars-dlq", Binding.DestinationType.QUEUE,
                "mars-dlq-exchange", "",null));



    }
}
