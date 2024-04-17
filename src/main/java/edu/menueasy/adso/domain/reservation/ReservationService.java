package edu.menueasy.adso.domain.reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService
{

    Long countReservation();

    List<Object[]>  getMonthlyReservationCounts();


    List<Reservation> getReservationsBetweenDates(LocalDateTime start, LocalDateTime end);
}
