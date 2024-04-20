package edu.menueasy.adso.domain.reservation;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@DisplayName("Test Service Implementation Class")
@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

  @Mock
  private ReservationValidator reservationValidator;

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

  @Test
  public void updateReservation(){
    Reservation reservation1 = new Reservation();
    reservation1.setName("Example");

    given(reservationRepository.save(any(Reservation.class))).willReturn(reservation1);

    Reservation updatedReservation = reservationService.updateReservation(new Reservation());

    then(reservationRepository).should().save(any(Reservation.class));
    assertThat(reservation1.getName()).isEqualTo(updatedReservation.getName());
  }

  @Test
  void checkReservation(){
    ReservationCheckDto check = new ReservationCheckDto(true);
    Reservation reservation = new Reservation();
    reservation.setId(1L);

    given(reservationRepository.findById(anyLong())).willReturn(Optional.of(reservation));
    given(reservationRepository.save(any(Reservation.class))).willReturn(reservation);

    Reservation savedReservation = reservationService.checkReservation(1l, check);

    then(reservationRepository).should().save(any(Reservation.class));
    assertThat(reservation.getId()).isEqualTo(savedReservation.getId());

  }


  @Test
  void countReservation(){
    Long count = 2L;
    given(reservationRepository.count()).willReturn(count);

    Long returnedCount1 = reservationService.countReservation();
    Long returnedCount2 = reservationService.countReservation();

    then(reservationRepository).should(times(2)).count();

    assertThat(count).isEqualTo(returnedCount1);
    assertThat(returnedCount1).isEqualTo(returnedCount2);
  }


}