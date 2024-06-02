package org.example.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import lombok.*;
import org.example.model.MoveType;
import org.example.model.Type;
import org.example.persistence.Move;
import org.example.persistence.Pokemon;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MoveDTO{

    private int id;
    private String name;
    private int power;
    private int pp;
    private int accuracy;
    private Type type;
    private MoveType moveType;

    public MoveDTO(Move move){
        this.id = move.getId();
        this.name = move.getName();
        this.power = move.getPower();
        this.pp = move.getPp();
        this.accuracy = move.getAccuracy();
        this.type = move.getType();
        this.moveType = move.getMoveType();
    }
}
