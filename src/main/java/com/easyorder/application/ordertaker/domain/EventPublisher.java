package com.easyorder.application.ordertaker.domain;

public interface EventPublisher {
    void publish(DomainEvent event);
}
