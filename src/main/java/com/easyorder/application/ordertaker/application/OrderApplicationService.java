package com.easyorder.application.ordertaker.application;


import com.easyorder.application.ordertaker.domain.IdGenerator;
import com.easyorder.application.ordertaker.domain.order.Order;
import com.easyorder.application.ordertaker.domain.order.OrderLine;
import com.easyorder.application.ordertaker.domain.order.OrderRepository;

import java.time.Clock;
import java.time.LocalDateTime;

public class OrderApplicationService {

    private final OrderRepository orderRepository;
    private final IdGenerator idGenerator;
    private final Clock clock;

    public OrderApplicationService(OrderRepository orderRepository, IdGenerator idGenerator, Clock clock) {
        this.orderRepository = orderRepository;
        this.idGenerator = idGenerator;
        this.clock = clock;
    }

//    @Transactional
    public String createOrder(String restaurantId) {
        String id = idGenerator.generate();
//        LocalDateTime creationDate = LocalDateTime.now(clock);
        Order order = new Order(id, restaurantId/*, creationDate*/);
        orderRepository.save(order);
        return id;
    }

    public void addItemToOrder(String orderId, String itemId) {
        Order order = orderRepository.findById(orderId);
        order.addOrderLine(new OrderLine("", "", 0, ""));
        orderRepository.save(order);
    }

    public void placeOrder(String orderId) {
        Order order = orderRepository.findById(orderId);
        LocalDateTime placeTime = LocalDateTime.now(clock);
        order.place(placeTime);
        orderRepository.save(order);
    }

//    @Transactional(readOnly = true)
    public Order getOrder(String orderId) {
        return orderRepository.findById(orderId);
    }

}
