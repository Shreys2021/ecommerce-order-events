package com.example.ordersystem.events;

import java.time.Instant;

public class PaymentReceivedEvent extends Event {
    private String orderId;
    private double amountPaid;

    public PaymentReceivedEvent(String eventId, Instant timestamp, String orderId, double amountPaid) {
        super(eventId, timestamp, "PaymentReceived");
        this.orderId = orderId;
        this.amountPaid = amountPaid;
    }

    public String getOrderId() { return orderId; }
    public double getAmountPaid() { return amountPaid; }

    @Override
    public String toString() {
        return "PaymentReceivedEvent{" +
                "orderId='" + orderId + '\'' +
                ", amountPaid=" + amountPaid +
                "} " + super.toString();
    }
}
