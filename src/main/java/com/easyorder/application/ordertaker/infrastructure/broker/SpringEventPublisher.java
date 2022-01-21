package com.easyorder.application.ordertaker.infrastructure.broker;

import com.easyorder.application.ordertaker.domain.DomainEvent;
import com.easyorder.application.ordertaker.domain.EventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
//@Profile("spring")
public class SpringEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public SpringEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void publish(DomainEvent event) {
        eventPublisher.publishEvent(event);
    }
}
