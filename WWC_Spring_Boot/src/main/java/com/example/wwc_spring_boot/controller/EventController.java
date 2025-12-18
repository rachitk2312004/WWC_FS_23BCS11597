package com.example.wwc_spring_boot.controller;

import com.example.wwc_spring_boot.model.Event;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/events")

public class EventController {
    private final List<Event> events = new ArrayList<>();

    // Get all events
    @GetMapping
    public List<Event> getEvents() {
        return events;
    }

    // Add a new event
    @PostMapping
    public String addEvent(@RequestBody Event event) {
        events.add(event);
        return "Event Added Successfully";
    }

    // Get a single event by index
    @GetMapping("/{id}")
    public Event getEvent(@PathVariable int id) {
        if (id < 0 || id >= events.size()) {
            throw new IndexOutOfBoundsException("Invalid event id: " + id);
        }
        return events.get(id);
    }

    // Delete an event by index
    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable int id) {
        if (id < 0 || id >= events.size()) {
            return "Invalid event id";
        }
        events.remove(id);
        return "Event Deleted Successfully";
    }

    // Update an existing event by index
    @PutMapping("/{id}")
    public String updateEvent(@PathVariable int id, @RequestBody Event event) {
        if (id < 0 || id >= events.size()) {
            return "Invalid event id";
        }
        events.set(id, event);
        return "Event Updated Successfully";
    }
}
