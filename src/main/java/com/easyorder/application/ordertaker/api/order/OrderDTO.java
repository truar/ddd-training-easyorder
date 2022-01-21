package com.easyorder.application.ordertaker.api.order;

import java.util.List;

public class OrderDTO {

    public final int totalPrice;
    public final int totalQuantity;
    public final List<OrderLineDTO> orderLines;

    public OrderDTO(int totalPrice, int totalQuantity, List<OrderLineDTO> orderLines) {
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
        this.orderLines = orderLines;
    }

    static class OrderLineDTO {
        public final String id;
        public final String name;
        public final int price;
        public final String comment;
        public final int quantity;
        public final int totalPrice;

        public OrderLineDTO(String id, String name, int price, String comment, int quantity, int totalPrice) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.comment = comment;
            this.quantity = quantity;
            this.totalPrice = totalPrice;
        }
    }
}
