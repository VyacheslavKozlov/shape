package ru.vyacheslavkozlov.firstrunday.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "models")
@JsonIdentityInfo( scope = Model.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "limitation")
    private int limitation;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    private Producer producer;

}
