package org.example.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dtos.PokemonDTO;
import org.example.model.Gender;
import org.example.model.Type;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pokemon {
    @Id
    private String dex_number;

    @ManyToMany(mappedBy = "pokemon")
    Set<Pokedex> pokedexes = new LinkedHashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pokemon_moves", joinColumns = @JoinColumn(name = "dex_number"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    Set<Move> moves = new LinkedHashSet<>();

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

   public Pokemon(PokemonDTO pokemonDTO){
        this.dex_number = pokemonDTO.getDex_number();
        this.name = pokemonDTO.getName();
        this.dexEntry = pokemonDTO.getDexEntry();
        this.hp = pokemonDTO.getHp();
        this.atk = pokemonDTO.getAtk();
        this.def = pokemonDTO.getDef();
        this.spatk = pokemonDTO.getSpatk();
        this.spdef = pokemonDTO.getSpdef();
        this.spd = pokemonDTO.getSpd();
        this.isShiny = pokemonDTO.isShiny();
        this.gender = pokemonDTO.getGender();
        this.height = pokemonDTO.getHeight();
        this.weight = pokemonDTO.getWeight();
        this.spriteUrl = pokemonDTO.getSpriteUrl();
        this.shinySpriteUrl = pokemonDTO.getShinySpriteUrl();
        this.typeOne = pokemonDTO.getTypeOne();
        this.typeTwo = pokemonDTO.getTypeTwo();
        this.moves = pokemonDTO.getMoves().stream().map(Move::new).collect(Collectors.toSet());
   }
}
