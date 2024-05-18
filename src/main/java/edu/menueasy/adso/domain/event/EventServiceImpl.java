package edu.menueasy.adso.domain.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event findEventById(Integer id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }
}