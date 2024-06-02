package org.example.daos;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.persistence.Pokemon;

public class PokemonDAO extends DAO<Pokemon, Integer>{

    private static PokemonDAO instance;

    private ObjectMapper objectMapper;
    private PokemonDAO (boolean isTesting){
        super(Pokemon.class, isTesting);
    }

    public static PokemonDAO getInstance(boolean isTesting){
        if(instance == null){
            instance = new PokemonDAO(isTesting);
        }
        return instance;
    }

}
