package edu.menueasy.adso.domain.event;

public interface EventService {

    Event findEventById(Integer id);

    Event updateEvent(Event event);
}