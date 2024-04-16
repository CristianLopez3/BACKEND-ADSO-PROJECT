package edu.menueasy.adso.domain.reservation;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ReservationServiceImplTest {
  @Mock
  private ReservationRepository reservationRepository;

  @InjectMocks
  private ReservationServiceImpl reservationService;

  @Test
  public void testCreateReservation() {
    Reservation reservation = new Reservation();
    when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
    reservationService.createReservation(reservation);
    verify(reservationRepository, times(1)).save(reservation);
  }

  @Test
  public void testGetAllReservations() {
    Reservation reservation1 = new Reservation();
    Reservation reservation2 = new Reservation();
    List<Reservation> reservations = Arrays.asList(reservation1, reservation2);
    when(reservationRepository.findAll()).thenReturn(reservations);
    List<Reservation> result = reservationService.getAllReservations();
    verify(reservationRepository, times(1)).findAll();
    assertEquals(reservations, result);
  }
}