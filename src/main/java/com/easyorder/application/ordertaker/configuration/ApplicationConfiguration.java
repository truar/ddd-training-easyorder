package com.easyorder.application.ordertaker.configuration;

import com.easyorder.application.ordertaker.application.OrderApplicationService;
import com.easyorder.application.ordertaker.domain.EventPublisher;
import com.easyorder.application.ordertaker.domain.IdGenerator;
import com.easyorder.application.ordertaker.domain.order.OrderRepository;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
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

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    /*
    To allow RabbitTemplate.convertAndSend to transform the object OrderPlaced in JSON,
    and fetching the object in the listener as a Map<String, Object>
     */
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return new Jackson2JsonMessageConverter(mapper);
    }


}
