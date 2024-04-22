package edu.menueasy.adso.controller;

import edu.menueasy.adso.domain.reservation.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/v1/reservations")
@CrossOrigin("*")
public class ReservationController {

    private final ReservationServiceImpl reservationService;

    @Autowired
    public ReservationController(ReservationServiceImpl reservationService) {
        this.reservationService = reservationService;
    }

    // Métodos para la creación y actualización de reservaciones
    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
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
        return ResponseEntity.ok(reservationService.checkReservation(id, reservation));
    }

    // Métodos para obtener reservaciones
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

    // Métodos para obtener estadísticas de reservaciones
    @GetMapping("/count")
    public ResponseEntity<Long> countReservation(){
        return ResponseEntity.ok(reservationService.countReservation());
    }

    @GetMapping("/unchecked-in-count")
    public ResponseEntity<Long> getUncheckedInReservationCount(){
        return ResponseEntity.ok(reservationService.getUncheckedInReservationCount());
    }

    @GetMapping("/mes")
    public ResponseEntity<Map<String, Integer>> getMonthlyReservations() {
        return ResponseEntity.ok(reservationService.getMonthlyReservationCounts());
    }

    @GetMapping("/between-dates")
    public ResponseEntity<Long> getReservationsBetweenDates(@RequestParam(name = "start") String start, @RequestParam(name = "end") String end) {
        LocalDateTime startDate = LocalDateTime.parse(start);
        LocalDateTime endDate = LocalDateTime.parse(end);
        return ResponseEntity.ok(reservationService.getReservationBetweenDate(startDate, endDate));
    }

    @GetMapping("/count-between-dates")
    public ResponseEntity<List<ReservationCountDto>> getReservationsCountBetweenDates(@RequestParam(name = "start") String start, @RequestParam(name = "end") String end) {
        LocalDateTime startDate = LocalDateTime.parse(start);
        LocalDateTime endDate = LocalDateTime.parse(end);
        return ResponseEntity.ok(reservationService.getReservationBetweenDates(startDate, endDate));
    }

    @GetMapping("/current-month")
    public ResponseEntity<Long> getReservationsForCurrentMonth() {
        return ResponseEntity.ok(reservationService.getReservationsForCurrentMonth());
    }

    @GetMapping("/previous-month")
    public ResponseEntity<Long> getReservationsForPreviousMonth() {
        return ResponseEntity.ok(reservationService.getReservationsForPreviousMonth());
    }

    @GetMapping("/compare-months")
    public ResponseEntity<Map<String, Long>> getReservationsForGivenMonths(@RequestParam(name = "start") String start, @RequestParam(name = "end") String end) {
        LocalDateTime startDate = LocalDateTime.parse(start);
        LocalDateTime endDate = LocalDateTime.parse(end);
        return ResponseEntity.ok(reservationService.getReservationsForGivenMonths(startDate, endDate));
    }




}