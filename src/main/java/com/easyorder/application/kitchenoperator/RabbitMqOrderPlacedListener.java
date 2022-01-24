package com.easyorder.application.kitchenoperator;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Profile("rabbit")
public class RabbitMqOrderPlacedListener {

    @RabbitListener(queues = "orderPlacedQueue")
    public void listen(Map<String, Object> in) {
        System.out.println("Message read from orderPlacedQueue : " + in);
    }
}
