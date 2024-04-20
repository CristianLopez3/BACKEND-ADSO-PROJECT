package edu.menueasy.adso.domain.reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {

    Reservation createReservation(Reservation reservation);

    List<Reservation> getAllReservations();

    List<Reservation> getCheckedInReservations();

    List<Reservation> getUncheckedInReservations();

    Reservation updateReservation(Reservation reservation);

    Reservation checkReservation(Long id, ReservationCheckDto reservationDto);

    Long countReservation();

    List<Object[]>  getMonthlyReservationCounts();

    Long getReservationBetweenDate(LocalDateTime start, LocalDateTime end);


}
