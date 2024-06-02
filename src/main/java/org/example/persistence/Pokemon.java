package org.example.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.Gender;
import org.example.model.Type;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pokemon {
    @Id
    private String dex_number;

    @ManyToMany(mappedBy = "pokemon")
    Set<Pokedex> pokedexes = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "pokemon_moves", joinColumns = @JoinColumn(name = "dex_number"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    Set<Move> moves = new HashSet<>();

    private String name;
    private String dexEntry;
    private int hp;
    private int atk;
    private int def;
    private int spatk;
    private int spdef;
    private int spd;
    private boolean isShiny;
    private Gender gender;
    private String height;
    private String weight;
    private String spriteUrl;
    private String shinySpriteUrl;

    @Enumerated(EnumType.STRING)
    private Type typeOne;

    @Enumerated(EnumType.STRING)
    private Type typeTwo;
}
