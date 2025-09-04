package com.example.ordersystem.events;

import java.time.Instant;

public class ShippingScheduledEvent extends Event {
    private String orderId;
    private String shippingDate;

    public ShippingScheduledEvent(String eventId, Instant timestamp, String orderId, String shippingDate) {
        super(eventId, timestamp, "ShippingScheduled");
        this.orderId = orderId;
        this.shippingDate = shippingDate;
    }

    public String getOrderId() { return orderId; }
    public String getShippingDate() { return shippingDate; }

    @Override
    public String toString() {
        return "ShippingScheduledEvent{" +
                "orderId='" + orderId + '\'' +
                ", shippingDate='" + shippingDate + '\'' +
                "} " + super.toString();
    }
}
