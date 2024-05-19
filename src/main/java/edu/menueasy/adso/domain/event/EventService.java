package edu.menueasy.adso.domain.event;
import org.springframework.web.multipart.MultipartFile;

public interface EventService {

    Event findEventById(Integer id);
    Event updateEvent(Integer id,  MultipartFile image);
    Event updateEvent(Integer id, Event eventDetails);

}