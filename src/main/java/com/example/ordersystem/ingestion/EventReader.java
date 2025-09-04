package com.example.ordersystem.ingestion;

import com.example.ordersystem.events.Event;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.ordersystem.events.*;
import com.example.ordersystem.Item;


import java.io.*;
import java.time.Instant;
import java.util.*;

public class EventReader {
    private ObjectMapper mapper = new ObjectMapper();

    public List<Event> readEvents(String filePath) {
        List<Event> events = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                JsonNode node = mapper.readTree(line);
                String eventType = node.get("eventType").asText();

                Event event = switch (eventType) {
                    case "OrderCreated" -> new OrderCreatedEvent(
                            node.get("eventId").asText(),
                            Instant.parse(node.get("timestamp").asText()),
                            node.get("orderId").asText(),
                            node.get("customerId").asText(),
                            parseItems(node.get("items")),
                            node.get("totalAmount").asDouble()
                    );

                    case "PaymentReceived" -> new PaymentReceivedEvent(
                            node.get("eventId").asText(),
                            Instant.parse(node.get("timestamp").asText()),
                            node.get("orderId").asText(),
                            node.get("amountPaid").asDouble()
                    );

                    case "ShippingScheduled" -> new ShippingScheduledEvent(
                            node.get("eventId").asText(),
                            Instant.parse(node.get("timestamp").asText()),
                            node.get("orderId").asText(),
                            node.get("shippingDate").asText()
                    );

                    case "OrderCancelled" -> new OrderCancelledEvent(
                            node.get("eventId").asText(),
                            Instant.parse(node.get("timestamp").asText()),
                            node.get("orderId").asText(),
                            node.get("reason").asText()
                    );

                    default -> {
                        System.out.println("⚠️ Unknown event type: " + eventType);
                        yield null;
                    }
                };

                if (event != null) {
                    events.add(event);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return events;
    }

    private List<Item> parseItems(JsonNode itemsNode) {
        List<Item> items = new ArrayList<>();
        if (itemsNode != null && itemsNode.isArray()) {
            for (JsonNode item : itemsNode) {
                String itemId = item.get("itemId").asText();
                int qty = item.get("qty").asInt();
                items.add(new Item(itemId, qty));
            }
        }
        return items;
    }
}
