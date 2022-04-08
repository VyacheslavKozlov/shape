package ru.vyacheslavkozlov.firstrunday.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "workout")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

}
