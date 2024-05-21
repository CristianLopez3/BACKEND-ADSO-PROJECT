package edu.menueasy.adso.domain.event;

import edu.menueasy.adso.s3.S3Buckets;
import edu.menueasy.adso.s3.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.menueasy.adso.domain.menu.image.ImageServiceImpl;
import edu.menueasy.adso.infra.exceptions.menu.ImageUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ImageServiceImpl filesService;
    private final S3Service s3Service;
    private final S3Buckets s3Buckets;

    @Value("${project.image}")
    private String PATH;


    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ImageServiceImpl filesServicer,
                            S3Service s3Service, S3Buckets s3Buckets) {
        this.eventRepository = eventRepository;
        this.filesService = filesServicer;
        this.s3Service = s3Service;
        this.s3Buckets = s3Buckets;
    }


    @Override
    public Event findEventById(Integer eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }


    @Override
    public Event updateEvent(Integer id, Event eventDetails) {
        Event existingEvent = eventRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Event with id " + id + " not found")
        );
        ;
        existingEvent.setTitle(eventDetails.getTitle());
        existingEvent.setDescription(eventDetails.getDescription());
        existingEvent.setDiscount(eventDetails.getDiscount());

        return eventRepository.save(existingEvent);
    }

    @Override
    public void updateEvent(Integer eventId, MultipartFile image) {
        checkIfEventExists(eventId);
        try {
            String eventImageId = UUID.randomUUID().toString();
            s3Service.putObject(
                    s3Buckets.getImages(),
                    "/events-images/%s/%s".formatted(eventId, eventImageId),
                    image.getBytes()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // TODO: store image to db
    }


    @Override
    public byte[] getEventImage(Integer eventId) {
        var existingEvent = eventRepository.findById(eventId).orElseThrow(
                () -> new IllegalArgumentException("Event with id " + eventId + " not found")
        );
        var eventImageId = "TODO";
        byte[] eventImage = s3Service.getObject(
                s3Buckets.getImages(),
                "/events-images/%s/%s".formatted(eventId, eventImageId)
        );
        // TODO: check if event id is empty or null
        return eventImage;

    }


    private void checkIfEventExists(Integer id) {
        Event existingEvent = eventRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Event with id " + id + " not found")
        );
    }


    private String uploadImage(MultipartFile image) {
        try {
            return filesService.uploadImage(PATH, image);
        } catch (IOException e) {
            throw new ImageUploadException(e.getMessage(), e);
        }
    }


//    @Override
//    public void updateEvent(Integer id, MultipartFile image) {
//        Event existingEvent = eventRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("Event with id " + id + " not found")
//        );
//        if (image != null) {
//            String imageUrl = uploadImage(image);
//            existingEvent.setUrl(imageUrl);
//        }
//        eventRepository.save(existingEvent);
//    }


}

