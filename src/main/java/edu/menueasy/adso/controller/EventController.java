package edu.menueasy.adso.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.menueasy.adso.domain.event.Event;
import edu.menueasy.adso.domain.event.EventService;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


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
    @Transactional
    public ResponseEntity<Event> updateEvent(@PathVariable Integer id, @RequestPart("event") String eventStr) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Event eventDetails = objectMapper.readValue(eventStr, Event.class);
        Event updatedEvent = eventService.updateEvent(id, eventDetails);
        return ResponseEntity.ok(updatedEvent);
    }

    @PutMapping("/{id}/picture")
    @Transactional
    public ResponseEntity<Event> updateEventImage(@PathVariable Integer id, @RequestParam("image") MultipartFile image) {
        Event updatedEvent = eventService.updateEvent(id, image);
        return ResponseEntity.ok(updatedEvent);
    }

}