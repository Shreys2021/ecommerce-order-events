package com.example.ordersystem.processor;

import com.example.ordersystem.Order;
import com.example.ordersystem.Status;
import com.example.ordersystem.events.*;
import com.example.ordersystem.observer.Observer;

import java.util.*;

/**
 * EventProcessor applies business logic to incoming events
 * and updates the Order state accordingly.
 */
public class EventProcessor {

    // Keep all orders in memory (orderId → Order)
    private Map<String, Order> orders = new HashMap<>();

    // Registered observers (Logger, Alert, etc.)
    private List<Observer> observers = new ArrayList<>();

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    private void notifyObservers(Event event, Order order) {
        for (Observer obs : observers) {
            obs.onEventProcessed(event, order);
        }
    }

    public void processEvent(Event event) {
        if (event instanceof OrderCreatedEvent created) {
            handleOrderCreated(created);

        } else if (event instanceof PaymentReceivedEvent payment) {
            handlePaymentReceived(payment);

        } else if (event instanceof ShippingScheduledEvent shipping) {
            handleShippingScheduled(shipping);

        } else if (event instanceof OrderCancelledEvent cancelled) {
            handleOrderCancelled(cancelled);

        } else {
            System.out.println("⚠️ Unsupported event: " + event.getEventType());
        }
    }

    private void handleOrderCreated(OrderCreatedEvent event) {
        Order order = new Order(
                event.getOrderId(),
                event.getCustomerId(),
                event.getItems(),
                event.getTotalAmount()
        );
        order.addEvent(event);
        orders.put(order.getOrderId(), order);

        System.out.println("✅ Order created: " + order.getOrderId());
        notifyObservers(event, order);
    }

    private void handlePaymentReceived(PaymentReceivedEvent event) {
        Order order = orders.get(event.getOrderId());
        if (order != null) {
            if (event.getAmountPaid() >= order.getTotalAmount()) {
                order.setStatus(Status.PAID);
            } else {
                // Optional: support partially paid
                order.setStatus(Status.PENDING);
                System.out.println("⚠️ Order " + order.getOrderId() + " partially paid!");
            }
            order.addEvent(event);
            System.out.println("💰 Payment received for: " + order.getOrderId());
            notifyObservers(event, order);
        } else {
            System.out.println("⚠️ Payment received for unknown order: " + event.getOrderId());
        }
    }

    private void handleShippingScheduled(ShippingScheduledEvent event) {
        Order order = orders.get(event.getOrderId());
        if (order != null) {
            order.setStatus(Status.SHIPPED);
            order.addEvent(event);
            System.out.println("📦 Shipping scheduled for: " + order.getOrderId());
            notifyObservers(event, order);
        }
    }

    private void handleOrderCancelled(OrderCancelledEvent event) {
        Order order = orders.get(event.getOrderId());
        if (order != null) {
            order.setStatus(Status.CANCELLED);
            order.addEvent(event);
            System.out.println("❌ Order cancelled: " + order.getOrderId());
            notifyObservers(event, order);
        }
    }

    public Map<String, Order> getOrders() {
        return orders;
    }
}
