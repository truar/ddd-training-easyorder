package com.easyorder.application.kitchenoperator;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("rabbit")
public class RabbitMqOrderPlacedListener {

    @RabbitListener(queues = "orderPlacedQueue")
    public void listen(String in) {
        System.out.println("Message read from orderPlacedQueue : " + in);
    }
}
