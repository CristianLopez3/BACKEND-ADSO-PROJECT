package edu.menueasy.adso.domain.Menu;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "tb_menu")
public class Menu {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;



}
