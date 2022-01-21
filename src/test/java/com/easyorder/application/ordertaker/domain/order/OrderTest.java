package com.easyorder.application.ordertaker.domain.order;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    public static final String ORDER_ID = "orderId";
    public static final String RESTAURANT_ID = "restaurantId";
    public static final String ORDER_LINE_ID = "orderLineId";
    public static final String ORDER_LINE_NAME = "orderLineName";
    public static final String ORDER_LINE_COMMENT = "orderLineComment";

    @Test
    void creates_an_order() {
        Order order = new Order(ORDER_ID, RESTAURANT_ID);

        assertThat(order.getId()).isEqualTo(ORDER_ID);
        assertThat(order.getRestaurantId()).isEqualTo(RESTAURANT_ID);
        assertThat(order.getOrderLines()).isEmpty();
        assertThat(order.getTotalPrice()).isEqualTo(0);
    }

    @Test
    void adds_an_order_line_to_an_order() {
        Order order = new Order(ORDER_ID, RESTAURANT_ID);
        OrderLine orderLine = new OrderLine(ORDER_LINE_ID, ORDER_LINE_NAME, 1000, ORDER_LINE_COMMENT);

        order.addOrderLine(orderLine);

        assertThat(order.getOrderLines()).hasSize(1);
        assertThat(order.getTotalPrice()).isEqualTo(1000);
    }

    @Test
    void removes_an_order_line_from_an_order() {
        Order order = new Order(ORDER_ID, RESTAURANT_ID);
        OrderLine orderLine = new OrderLine(ORDER_LINE_ID, ORDER_LINE_NAME, 1000, ORDER_LINE_COMMENT);
        order.addOrderLine(orderLine);

        order.removeOrderLine(ORDER_LINE_ID);

        assertThat(order.getOrderLines()).hasSize(0);
        assertThat(order.getTotalPrice()).isEqualTo(0);
    }

    @Test
    void increases_order_line_quantity() {
        Order order = new Order(ORDER_ID, RESTAURANT_ID);
        OrderLine orderLine = new OrderLine(ORDER_LINE_ID, ORDER_LINE_NAME, 1000, ORDER_LINE_COMMENT);
        order.addOrderLine(orderLine);

        order.incrementOrderLineQuantity(ORDER_LINE_ID);

        assertThat(order.getOrderLines()).hasSize(1);
        assertThat(order.getTotalQuantity()).isEqualTo(2);
        assertThat(order.getTotalPrice()).isEqualTo(2000);
    }

    @Test
    void place_order() {
        Order order = new Order(ORDER_ID, RESTAURANT_ID);
        OrderLine orderLine = new OrderLine(ORDER_LINE_ID, ORDER_LINE_NAME, 1000, ORDER_LINE_COMMENT);
        order.addOrderLine(orderLine);
        LocalDateTime placeTime = LocalDateTime.now();

        order.place(placeTime);

        assertThat(order.getPlaceTime()).isEqualTo(placeTime);
    }
}
