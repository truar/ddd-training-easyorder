package com.easyorder.application.ordertaker.domain.order;

public class OrderLine {
    private final String id;
    private final String name;
    private final int price;
    private final String comment;
    private int quantity = 1;

    public OrderLine(String id, String name, int price, String comment) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.comment = comment;
    }

    public int getTotalPrice() {
        return this.quantity * this.price;
    }

    public String getId() {
        return id;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getComment() {
        return comment;
    }
}
