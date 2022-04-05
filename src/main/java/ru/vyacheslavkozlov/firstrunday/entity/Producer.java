package ru.vyacheslavkozlov.firstrunday.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "producers")
@JsonIdentityInfo(scope = Producer.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

//    @JsonIgnore
//    @OneToMany(mappedBy = "producer", fetch = FetchType.EAGER)
//    private List<Model> models;
}
