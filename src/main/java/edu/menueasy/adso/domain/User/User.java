package edu.menueasy.adso.domain.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigInteger;

@Entity
@Table(name = "tb_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  private Integer id;

  @Column(name = "name", nullable = false, length = 50)
  @Getter
  private String name;

  @Column(name = "lastName", nullable = false, length = 50)
  @Getter
  private String lastName;

  @Column(name = "identification", nullable = false, length = 50, unique = true)
  @Getter
  private String identification;

  @Column(name = "cellphone", nullable = false, unique = true)
  @Getter
  private Long cellphone;

  @Column(name = "email", nullable = false, unique = true)
  @Getter
  private String email;

  @Column(name = "password", nullable = false)
  @Getter
  private String password;

  @Column(name = "role", nullable = false, length = 50)
  @Getter
  private String role;




}
