package com.example.ordersystem.observer;

import com.example.ordersystem.Order;
import com.example.ordersystem.Status;
import com.example.ordersystem.events.Event;

public class AlertObserver implements Observer {
    @Override
    public void onEventProcessed(Event event, Order order) {
        if (order != null && 
            (order.getStatus() == Status.CANCELLED || order.getStatus() == Status.SHIPPED)) {
            System.out.println("[ALERT 🚨] Order " + order.getOrderId() +
                    " changed to status: " + order.getStatus());
        }
    }
}
