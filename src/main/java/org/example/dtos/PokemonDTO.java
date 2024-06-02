package org.example.dtos;

import jakarta.persistence.*;
import lombok.*;
import org.example.model.Gender;
import org.example.model.Type;
import org.example.persistence.Move;
import org.example.persistence.Pokedex;
import org.example.persistence.Pokemon;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PokemonDTO {

    private String dex_number;
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
    private Type typeOne;
    private Type typeTwo;

    Set<MoveDTO> moves = new HashSet<>();

    public PokemonDTO (Pokemon pokemon){
        this.dex_number = pokemon.getDex_number();
        this.name = pokemon.getName();
        this.dexEntry = pokemon.getDexEntry();
        this.hp = pokemon.getHp();
        this.atk = pokemon.getAtk();
        this.def = pokemon.getDef();
        this.spatk = pokemon.getSpatk();
        this.spdef = pokemon.getSpdef();
        this.spd = pokemon.getSpd();
        this.isShiny = pokemon.isShiny();
        this.gender = pokemon.getGender();
        this.height = pokemon.getHeight();
        this.weight = pokemon.getWeight();
        this.spriteUrl = pokemon.getSpriteUrl();
        this.shinySpriteUrl = pokemon.getShinySpriteUrl();
        this.typeOne = pokemon.getTypeOne();
        this.typeTwo = pokemon.getTypeTwo();
        this.moves = pokemon.getMoves().stream().map(MoveDTO::new).collect(Collectors.toSet());
    }
}
