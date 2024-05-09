package edu.menueasy.adso.domain.reservation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationValidator reservationValidator;

    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  ReservationValidator reservationValidator) {
        this.reservationRepository = reservationRepository;
        this.reservationValidator = reservationValidator;
    }

    // Reservation creation and update methods
    @Override
    public Reservation createReservation(Reservation reservation) {
        reservationValidator.validate(reservation);
        reservation.setCheckedIn(false);
        return reservationRepository.save(reservation);
    }



    @Override
    public Reservation updateReservation(Reservation reservation) {
        if(reservation.getId() == null || reservationRepository.findById(reservation.getId()).isEmpty()){
            throw new IllegalArgumentException("Reservation ID is required for updating reservation");
        }
        reservationValidator.validate(reservation);
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation checkReservation(Long id, ReservationCheckDto reservationDto) {
        Reservation reservation = findReservationById(id);
        reservation.setCheckedIn(reservationDto.checkedIn());
        return reservationRepository.save(reservation);
    }

    // Reservation retrieval methods


    @Override
    public List<Reservation> getCheckedInReservations() {
        return reservationRepository.findByCheckedInIsTrue();
    }

    @Override
    public List<Reservation> getUncheckedInReservations() {
        return reservationRepository.findByCheckedInIsFalse();
    }

    // Reservation statistics methods
    @Override
    public Long countReservation() {
        return reservationRepository.count();
    }

    @Override
    public Long getUncheckedInReservationCount() {
        return reservationRepository.countByCheckedInFalse();
    }

    @Override
    public Map<String, Integer> getMonthlyReservationCounts() {
        return calculateMonthlyReservationCounts();
    }

    @Override
    public Long getReservationBetweenDate(LocalDateTime start, LocalDateTime end) {
        return reservationRepository.countReservationsWithinDateRange(start, end);
    }

    @Override
    public List<ReservationCountDto> getReservationBetweenDates(LocalDateTime start, LocalDateTime end) {
        return reservationRepository.findReservationsCountBetweenDates(start, end);
    }

    @Override
    public Long getReservationsForCurrentMonth() {
        return getReservationsForSpecificMonth(LocalDateTime.now());
    }

    @Override
    public Long getReservationsForPreviousMonth() {
        return getReservationsForSpecificMonth(LocalDateTime.now().minusMonths(1));
    }

    @Override
    public Map<String, Long> getReservationsForGivenMonths(LocalDateTime start, LocalDateTime end) {
        Map<String, Long> reservationCounts = new HashMap<>();
        reservationCounts.put("Start Month", getReservationsForSpecificMonth(start));
        reservationCounts.put("End Month", getReservationsForSpecificMonth(end));
        return reservationCounts;
    }

    @Override
    public Long getReservationsForSpecificMonth(LocalDateTime date) {
        LocalDateTime start = date.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime end = date.withDayOfMonth(date.toLocalDate().lengthOfMonth()).withHour(23).withMinute(59).withSecond(59);
        return reservationRepository.countReservationsForSpecificMonth(start, end);
    }

    @Override
    public Page<Reservation> getReservations(Pageable pageable) {
        return reservationRepository.findAll(pageable);
    }


    // Helper methods
    private Reservation findReservationById(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't find this reservation, try again"));
    }



    private Map<String, Integer> calculateMonthlyReservationCounts() {
        List<Object[]> monthlyCounts = reservationRepository.findMonthlyReservationCounts();
        Map<String, Integer> monthlyReservations = new LinkedHashMap<>();

        for (Object[] result : monthlyCounts) {
            Integer monthValue = (Integer) result[0];
            Month month = Month.of(monthValue);
            Long count = (Long) result[1];
            String monthName = month.getDisplayName(TextStyle.SHORT, Locale.US).toLowerCase();
            monthlyReservations.put(monthName, count.intValue());
        }

        for (Month month : Month.values()) {
            String monthName = month.getDisplayName(TextStyle.SHORT, Locale.US).toLowerCase();
            monthlyReservations.putIfAbsent(monthName, 0);
        }

        return monthlyReservations;
    }
}