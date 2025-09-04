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
            filePath = args[0]; 
        }

        
        EventReader reader = new EventReader();
        EventProcessor processor = new EventProcessor();

        
        processor.registerObserver(new LoggerObserver());
        processor.registerObserver(new AlertObserver());

       
        List<Event> events = reader.readEvents(filePath);

        System.out.println("📂 Loaded " + events.size() + " events from file.");

       
        for (Event event : events) {
            processor.processEvent(event);
        }

       
        System.out.println("\n📌 Final Orders:");
        for (Map.Entry<String, Order> entry : processor.getOrders().entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}
