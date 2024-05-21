package edu.menueasy.adso.domain.event;

import org.springframework.web.multipart.MultipartFile;

public interface EventService {

    Event findEventById(Integer id);

    void updateEvent(Integer id, MultipartFile image);

    Event updateEvent(Integer id, Event eventDetails);

    byte[] getEventImage(Integer eventId);

}