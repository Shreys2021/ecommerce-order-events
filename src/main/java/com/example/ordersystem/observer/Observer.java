package com.example.ordersystem.observer;

import com.example.ordersystem.Order;
import com.example.ordersystem.events.Event;

public interface Observer {
    void onEventProcessed(Event event, Order order);
}
