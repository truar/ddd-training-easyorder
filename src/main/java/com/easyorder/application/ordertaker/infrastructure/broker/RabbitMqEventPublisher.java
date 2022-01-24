package com.easyorder.application.ordertaker.infrastructure.broker;

import com.easyorder.application.ordertaker.domain.DomainEvent;
import com.easyorder.application.ordertaker.domain.EventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("rabbit")
public class RabbitMqEventPublisher implements EventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMqEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(DomainEvent event) {
        rabbitTemplate.convertAndSend("orderPlacedQueue", event);
    }
}
