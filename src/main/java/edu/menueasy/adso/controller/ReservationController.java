package edu.menueasy.adso.controller;

import edu.menueasy.adso.domain.reservation.ReservationCheckDto;
import edu.menueasy.adso.domain.reservation.ReservationService;
import edu.menueasy.adso.domain.reservation.ReservationServiceImpl;
import edu.menueasy.adso.domain.reservation.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


}
