package edu.menueasy.adso.domain.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCheckedInTrue();
    List<Reservation> findByCheckedInFalse();


    @Query("""
    SELECT MONTH(r.reservationDate), COUNT(r) FROM Reservation r WHERE YEAR(r.reservationDate) = YEAR(CURRENT_DATE()) GROUP BY MONTH(r.reservationDate)
    """)
    List<Object[]> getMonthlyReservationCounts();


}
