package edu.menueasy.adso.domain.book;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BookingTest {


    @Test
    void testCreateBooking(){
        Booking book1 = new Booking(1, "Today", "tomorrow", "morning");
        Booking book2 = new Booking(1, "Today", "tomorrow", "morning");
        assertEquals(book1, book2);
        assertEquals(book1.getName(), book2.getName());
    }

}
