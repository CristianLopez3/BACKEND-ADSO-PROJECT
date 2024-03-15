package edu.menueasy.adso.domain.book;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl {

    private BookingRepository bookingRepository;

}
