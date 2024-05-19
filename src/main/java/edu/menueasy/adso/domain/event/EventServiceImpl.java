package edu.menueasy.adso.domain.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.menueasy.adso.domain.menu.image.ImageServiceImpl;
import edu.menueasy.adso.infra.exceptions.menu.ImageUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final ImageServiceImpl filesService;

    @Value("${project.image}")
    private String PATH;


    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ImageServiceImpl filesServicer) {
        this.eventRepository = eventRepository;
        this.filesService = filesServicer;
    }

    @Override
    public Event findEventById(Integer id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public Event updateEvent(Integer id, Event eventDetails, MultipartFile image) {
        Event existingEvent = eventRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Event with id " + id + " not found")
        );
        if (image != null) {
            String imageUrl = uploadImage(image);
            existingEvent.setUrl(imageUrl);
        }
        existingEvent.setTitle(eventDetails.getTitle());
        existingEvent.setDescription(eventDetails.getDescription());

        return eventRepository.save(existingEvent);
    }

    private String uploadImage(MultipartFile image) {
        try {
            return filesService.uploadImage(PATH, image);
        } catch (IOException e) {
            throw new ImageUploadException(e.getMessage(), e);
        }
    }


}

