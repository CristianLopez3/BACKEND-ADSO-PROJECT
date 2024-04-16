package edu.menueasy.adso.domain.reservation;

import java.util.List;

public interface ReservationService
{

    Long countReservation();

    List<Object[]>  getMonthlyReservationCounts();
}
