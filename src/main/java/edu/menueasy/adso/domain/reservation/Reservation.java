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
    @Column(name = "phone_number")
    private String phonenumber;
    private String email;

    @Column(columnDefinition = "TIMESTAMP", name = "reservation_date")
    private LocalDateTime reservationdate;

    @Column(name = "number_of_people")
    private Integer numberofpeople;
    private String description;
    @Column(name = "checked_in")
    private Boolean checkedin;


}
