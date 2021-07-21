package com.example.rabbitmq.service;

import com.example.rabbitmq.model.Employee;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${javainuse.rabbitmq.exchange}")
    private String exchange;

    @Value("${javainuse.rabbitmq.routingkey}")
    private String routingkey;

    public void send(Employee company) {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(exchange, routingkey, company);
        }
        System.out.println("Send msg = " + company);
    }
}