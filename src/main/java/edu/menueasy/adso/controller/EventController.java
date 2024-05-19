package edu.menueasy.adso.controller;

import edu.menueasy.adso.domain.event.Event;
import edu.menueasy.adso.domain.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Integer id) {
        Event event = eventService.findEventById(id);
        return event != null ? ResponseEntity.ok(event) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Integer id, @RequestBody Event eventDetails) {
        Event existingEvent = eventService.findEventById(id);
        if (existingEvent == null) {
            return ResponseEntity.notFound().build();
        }
        existingEvent.setTitle(eventDetails.getTitle());
        existingEvent.setDescription(eventDetails.getDescription());
        existingEvent.setUrl(eventDetails.getUrl());
        Event updatedEvent = eventService.updateEvent(existingEvent);
        return ResponseEntity.ok(updatedEvent);
    }
}