package com.easyorder.application.ordertaker.application;

import com.easyorder.application.ordertaker.domain.IdGenerator;
import com.easyorder.application.ordertaker.domain.order.Order;
import com.easyorder.application.ordertaker.domain.order.OrderRepository;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.assertThat;

class OrderApplicationServiceTest {

    private final IdGenerator fixedIdGenerator = () -> "orderId";
    private final OrderRepository inMemoryOrderRepository = new OrderRepository() {

        private List<Order> orders = new ArrayList<>();

        @Override
        public void save(Order order) {
            this.orders.add(order);
        }

        @Override
        public Order findById(String orderId) {
            return orders.stream()
                    .filter(order -> order.getId().equals(orderId))
                    .findFirst().orElseThrow(RuntimeException::new);
        }
    };
    private final Clock clock = Clock.fixed(LocalDateTime.MIN.toInstant(UTC), UTC);

    @Test
    void should_create_an_order() {
        OrderApplicationService service = new OrderApplicationService(inMemoryOrderRepository, fixedIdGenerator, clock);

        String orderId = service.createOrder("restaurantId");

        assertThat(orderId).isEqualTo("orderId");
        Order order = inMemoryOrderRepository.findById(orderId);
        assertThat(order).isNotNull();
    }
}
