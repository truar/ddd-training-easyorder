package com.easyorder.application.ordertaker.domain.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final String id;
    private final String restaurantId;
    private List<OrderLine> orderLines = new ArrayList<>();
    private LocalDateTime placeTime;

    public Order(String id, String restaurantId) {
        this.id = id;
        this.restaurantId = restaurantId;
    }

    public void addOrderLine(OrderLine orderLine) {
        orderLines.add(orderLine);
    }

    public void removeOrderLine(String orderLineId) {
        orderLines.removeIf(orderLine -> orderLine.getId().equals(orderLineId));
    }

    public void incrementOrderLineQuantity(String orderLineId) {
        orderLines.stream()
                .filter(orderLine -> orderLine.getId().equals(orderLineId))
                .findFirst()
                .ifPresent(OrderLine::incrementQuantity);
    }

    public OrderPlaced place(LocalDateTime placeTime) {
        // TODO Check if orderLines is empty
        this.placeTime = placeTime;
        return new OrderPlaced(id, orderLines /*, placeTime*/);
    }

    public String getId() {
        return id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public int getTotalPrice() {
        return this.orderLines.stream()
                .mapToInt(OrderLine::getTotalPrice).sum();
    }

    public int getTotalQuantity() {
        return this.orderLines.stream()
                .mapToInt(OrderLine::getQuantity).sum();
    }

    public LocalDateTime getPlaceTime() {
        return placeTime;
    }

    public void setPlaceTime(LocalDateTime placeTime) {
        this.placeTime = placeTime;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
}
