package edu.menueasy.adso.domain.Menu;

import edu.menueasy.adso.domain.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_menu")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name = "title", nullable = false)
  private String title;
  @Column(name = "description", nullable = false)
  private String description;
  @Column(name = "price", nullable = false)
  private Double price;
  @Column(name = "state", nullable = false)
  private Boolean state;
  @JoinColumn(name = "id_category", nullable = false)
  @ManyToOne()
  private Category category;



}
