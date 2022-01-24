package com.easyorder.application.ordertaker.infrastructure.persistence.order.inmemory;

import com.easyorder.application.ordertaker.domain.order.Order;
import com.easyorder.application.ordertaker.domain.order.OrderRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("inmemory")
public class InMemoryOrderRepository implements OrderRepository {

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
}
