package com.example.ordersystem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.ordersystem.events.Event;

public class Order {
    private String orderId;
    private String customerId;
    private List<Item> items = new ArrayList<>();
private double totalAmount = 0.0;
    private Status status = Status.PENDING;

    private List<Event> eventHistory = new ArrayList<>();

    // We'll add eventHistory later once events exist.

    public Order() { }

    public Order(String orderId, String customerId, List<Item> items, double totalAmount) {
    this.orderId = orderId;
    this.customerId = customerId;
    if (items != null) this.items.addAll(items);
    this.totalAmount = totalAmount;
}

    public String getOrderId() { return orderId; }
    public String getCustomerId() { return customerId; }
    public List<Item> getItems() { return items; }
public double getTotalAmount() { return totalAmount; }
    public Status getStatus() { return status; }

    public void setOrderId(String orderId) { this.orderId = orderId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public void setItems(List<Item> items) {
        this.items.clear();
        if (items != null) this.items.addAll(items);
    }
public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public void setStatus(Status status) { this.status = status; }

    public void addEvent(Event event) {
        if (event != null) {
            eventHistory.add(event);
        }
    }
    
    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", items=" + items +
                ", totalAmount=" + totalAmount +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }
}
