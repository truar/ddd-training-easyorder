package com.easyorder.application.ordertaker.domain.order;

public interface OrderRepository {

    void save(Order order);

    Order findById(String orderId);
}
