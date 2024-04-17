package edu.menueasy.adso.domain.reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService
{

    Long countReservation();

    List<Object[]>  getMonthlyReservationCounts();

    Long getReservationBetweenDate(LocalDateTime start, LocalDateTime end);


}
