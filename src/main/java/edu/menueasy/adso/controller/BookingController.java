package edu.menueasy.adso.controller;

import edu.menueasy.adso.domain.book.BookServiceImpl;
import edu.menueasy.adso.domain.book.Booking;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookingController {


    private BookServiceImpl bookService;

    @GetMapping()
    public ResponseEntity<Booking> getAll(){
        // todo
        return null;
    }


}
