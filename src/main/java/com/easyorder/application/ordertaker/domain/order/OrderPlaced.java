package com.easyorder.application.ordertaker.domain.order;

import com.easyorder.application.ordertaker.domain.DomainEvent;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class OrderPlaced extends DomainEvent {

    private final String orderId;
    private final String restaurantId = "";
    private final String qrCodeId = "";
    private final List<OrderedItem> orderedItems;
    public OrderPlaced(String orderId, List<OrderLine> orderLines) {
        this.orderId = orderId;
        this.orderedItems = orderLines.stream()
                .map(orderLine -> new OrderedItem(orderLine.getName(), orderLine.getQuantity(), orderLine.getComment()))
                .collect(toList());
    }

    private class OrderedItem {
        private final String name;
        private final int quantity;
        private final String comment;

        public OrderedItem(String name, int quantity, String comment) {
            this.name = name;
            this.quantity = quantity;
            this.comment = comment;
        }

        @Override
        public String toString() {
            return "OrderedItem{" +
                    "name='" + name + '\'' +
                    ", quantity=" + quantity +
                    ", comment='" + comment + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "OrderPlaced{" +
                "orderId='" + orderId + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                ", qrCodeId='" + qrCodeId + '\'' +
                ", orderedItems=" + orderedItems +
                '}';
    }
}
