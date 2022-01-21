package com.easyorder.application.ordertaker.configuration;

import com.easyorder.application.ordertaker.application.OrderApplicationService;
import com.easyorder.application.ordertaker.domain.EventPublisher;
import com.easyorder.application.ordertaker.domain.IdGenerator;
import com.easyorder.application.ordertaker.domain.order.OrderRepository;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    public OrderApplicationService orderApplicationService(IdGenerator idGenerator, OrderRepository orderRepository,
                                                           Clock clock, EventPublisher eventPublisher) {
        return new OrderApplicationService(orderRepository, idGenerator, clock, eventPublisher);
    }

    @Bean
    public Queue orderPlacedQueue() {
        return new Queue("orderPlacedQueue", false);
    }

}
