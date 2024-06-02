package org.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.persistence.Pokedex;
import org.example.persistence.Pokemon;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PokedexDTO{

    private int id;
    private Set<PokemonDTO> pokemon = new HashSet<>();

    public PokedexDTO(Pokedex pokedex){
        this.id = pokedex.getId();
        this.pokemon = pokedex.getPokemon().stream().map(PokemonDTO::new).collect(Collectors.toSet());
    }
}
