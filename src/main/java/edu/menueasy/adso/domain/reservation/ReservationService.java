package edu.menueasy.adso.domain.reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService
{

    List<Object[]>  getMonthlyReservationCounts();


    List<Reservation> getReservationsBetweenDates(LocalDateTime start, LocalDateTime end);
}
