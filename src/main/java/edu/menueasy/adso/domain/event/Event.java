package edu.menueasy.adso.domain.event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.lang.model.element.Name;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Event")
@Table(name = "tb_event")

public class Event {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column ( name = "title")
    private String title;

    @Column( name = "description", length = 100)
    private String description;

    @Column( name = "discount")
    private Integer discount;

    @Column (name = "url")
    private String url;

}
