package com.example.ordersystem;

import com.example.ordersystem.events.Event;
import com.example.ordersystem.ingestion.EventReader;
import com.example.ordersystem.observer.AlertObserver;
import com.example.ordersystem.observer.LoggerObserver;
import com.example.ordersystem.processor.EventProcessor;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/main/resources/sample-events.jsonl";
        if (args.length > 0) {
            filePath = args[0]; // allow custom input file
        }

        // 1. Create event reader + processor
        EventReader reader = new EventReader();
        EventProcessor processor = new EventProcessor();

        // 2. Register observers
        processor.registerObserver(new LoggerObserver());
        processor.registerObserver(new AlertObserver());

        // 3. Read events from file
        List<Event> events = reader.readEvents(filePath);

        System.out.println("📂 Loaded " + events.size() + " events from file.");

        // 4. Process each event
        for (Event event : events) {
            processor.processEvent(event);
        }

        // 5. Print final order states
        System.out.println("\n📌 Final Orders:");
        for (Map.Entry<String, Order> entry : processor.getOrders().entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}
