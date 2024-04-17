package edu.menueasy.adso.domain.reservation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Reservation")
@Table(name = "tb_reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;
    private String email;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime reservationDate;

    private Integer numberOfPeople;
    private String description;
    private Boolean checkedIn;


}
