package ru.vyacheslavkozlov.firstrunday.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "inventory")
@JsonIdentityInfo( scope = Inventory.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "pair_number")
    private int pairNumber;

    @Column(name = "note")
    private String note;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

}
