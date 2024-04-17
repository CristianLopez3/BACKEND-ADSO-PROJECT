package edu.menueasy.adso.controller;

import edu.menueasy.adso.domain.reservation.ReservationCheckDto;
import edu.menueasy.adso.domain.reservation.ReservationService;
import edu.menueasy.adso.domain.reservation.ReservationServiceImpl;
import edu.menueasy.adso.domain.reservation.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/reservations")
@CrossOrigin("*")
public class ReservationController {


    private ReservationServiceImpl reservationService;

    @Autowired
    public ReservationController(ReservationServiceImpl reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/checked-in")
    public List<Reservation> getCheckedInReservations() {
        return reservationService.getCheckedInReservations();
    }

    @GetMapping("/unchecked-in")
    public List<Reservation> getUncheckedInReservations() {
        return reservationService.getUncheckedInReservations();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        if (!id.equals(reservation.getId())) {
            throw new IllegalArgumentException("ID in the path and ID in the reservation object must be the same.");
        }
        return ResponseEntity.ok(reservationService.updateReservation(reservation));
    }

    @PatchMapping("/check/{id}")
    public ResponseEntity<Reservation> checkReservation(@PathVariable("id") Long id, @RequestBody ReservationCheckDto reservation){
        System.out.println(reservation.checkedIn());
        ResponseEntity<Reservation> ok = ResponseEntity.ok(reservationService.checkReservation(id, reservation));
        return ok;
    }

    @PatchMapping("/patch")
    public String patch(){
        return "Greetings from patch";
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countReservation(){
        long countReservation = reservationService.countReservation();
        return ResponseEntity.ok(reservationService.countReservation());
    }

    @GetMapping("/mes")
    public Map<String, Integer> getMonthlyReservations() {
        List<Object[]> monthlyCounts = reservationService.getMonthlyReservationCounts();
        Map<String, Integer> monthlyReservations = new HashMap<>();

        for (Object[] result : monthlyCounts) {
            Month month = (Month) result[0];
            Long count = (Long) result[1];
            monthlyReservations.put(month.toString(), count.intValue());
        }

        // Fill in missing months with zero reservations
        for (Month month : Month.values()) {
            monthlyReservations.putIfAbsent(month.toString(), 0);
        }

        return monthlyReservations;
    }

    @PostMapping("/between-dates")
    public ResponseEntity<List<Reservation>> getReservationsBetweenDates(@RequestBody String start,
                                                                         @RequestBody String end) {
        LocalDateTime startDate = LocalDateTime.parse(start);
        LocalDateTime endDate = LocalDateTime.parse(end);
        List<Reservation> reservations = reservationService.getReservationsBetweenDates(startDate, endDate);
        return ResponseEntity.ok().body(reservations);
    }


}