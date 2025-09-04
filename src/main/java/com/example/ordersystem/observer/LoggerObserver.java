package com.example.ordersystem.observer;

import com.example.ordersystem.Order;
import com.example.ordersystem.events.Event;

public class LoggerObserver implements Observer {
    @Override
    public void onEventProcessed(Event event, Order order) {
        System.out.println("[Logger] Event processed: " + event.getEventType() +
                " for Order: " + (order != null ? order.getOrderId() : "N/A") +
                " | Status: " + (order != null ? order.getStatus() : "N/A"));
    }
}
