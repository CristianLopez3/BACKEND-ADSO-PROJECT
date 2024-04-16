package edu.menueasy.adso.domain.menu;

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

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "price")
  private Double price;

  @Column(name = "state")
  private Boolean state;

  @Column(name = "imageName")
  private String imageName;

  @JoinColumn(name = "category")
  @ManyToOne()
  private Category category;



}
