package edu.menueasy.adso.domain.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    long count();

    List<Reservation> findByCheckedinIsTrue();
    List<Reservation> findByCheckedinIsFalse();

    @Query("""
    SELECT MONTH(r.reservationdate), COUNT(r)
    FROM Reservation r
    WHERE YEAR(r.reservationdate) = YEAR(CURRENT_DATE()) GROUP BY MONTH(r.reservationdate)
    """)
    List<Object[]> findMonthlyReservationCounts();

    @Query("""
    SELECT COUNT(r) FROM Reservation r WHERE r.reservationdate BETWEEN :startDate AND :endDate
    """)
    Long countReservationsWithinDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


}
