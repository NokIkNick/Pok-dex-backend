package org.example.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.MoveType;
import org.example.model.Type;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Move {
    @Id
    private int id;

    private String name;
    private String description;
    private int power;
    private int pp;
    private int accuracy;


    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private MoveType moveType;

    @ManyToMany(mappedBy = "moves")
    Set<Pokemon> pokemon = new HashSet<>();

}
