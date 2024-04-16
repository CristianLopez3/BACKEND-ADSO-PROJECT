package edu.menueasy.adso.domain.reservation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationValidator reservationValidator;

    public Reservation createReservation(Reservation reservation) {
        reservationValidator.validate(reservation);
        reservation.setCheckedIn(false); // Set checkedIn to false when creating a new reservation
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }


    public List<Reservation> getCheckedInReservations() {
        return reservationRepository.findByCheckedInTrue();
    }

    public List<Reservation> getUncheckedInReservations() {
        return reservationRepository.findByCheckedInFalse();
    }

    public Reservation updateReservation(Reservation reservation) {
        reservationValidator.validate(reservation);
        return reservationRepository.save(reservation);
    }

    public Reservation checkReservation(Long id, ReservationCheckDto reservationDto) {
        Reservation reservation =  reservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't find this reservation, try again"));
        reservation.setCheckedIn(reservationDto.checkedIn());
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Object[]> getMonthlyReservationCounts() {
        return reservationRepository.getMonthlyReservationCounts();
    }
    @Override
    public Long countReservation(){
        return reservationRepository.count();
    }

}
