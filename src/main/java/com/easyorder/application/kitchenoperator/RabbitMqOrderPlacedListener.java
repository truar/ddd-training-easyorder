package com.easyorder.application.kitchenoperator;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class RabbitMqOrderPlacedListener {

    @RabbitListener(queues = "orderPlacedQueue")
    public void listen(String in) {
        System.out.println("Message read from orderPlacedQueue : " + in);
    }
}
