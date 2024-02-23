package edu.menueasy.adso.domain.User;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

@Entity
@Table(name = "tb_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "lastName", nullable = false, length = 50)
  private String lastName;

  @Column(name = "identification", nullable = false, length = 50, unique = true)
  private String identification;

  @Column(name = "cellphone", nullable = false, unique = true)
  private BigInteger cellphone;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "role", nullable = false, length = 50)
  private Role role;




}
