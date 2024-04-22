package edu.menueasy.adso.domain.reservation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ReservationService {

    Reservation createReservation(Reservation reservation);

    List<Reservation> getAllReservations();

    List<Reservation> getCheckedInReservations();

    List<Reservation> getUncheckedInReservations();

    Reservation updateReservation(Reservation reservation);

    Reservation checkReservation(Long id, ReservationCheckDto reservationDto);

    Long countReservation();

    Map<String, Integer> getMonthlyReservationCounts();

    Long getReservationBetweenDate(LocalDateTime start, LocalDateTime end);

    List<ReservationCountDto> getReservationBetweenDates(LocalDateTime start, LocalDateTime end);

    Long getUncheckedInReservationCount();

    Long getReservationsForCurrentMonth();

    Long getReservationsForPreviousMonth();

    Map<String, Long> getReservationsForGivenMonths(LocalDateTime start, LocalDateTime end);

    Long getReservationsForSpecificMonth(LocalDateTime date);

}
