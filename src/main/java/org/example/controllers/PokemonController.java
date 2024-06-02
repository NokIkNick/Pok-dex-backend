package org.example.controllers;

import org.example.daos.PokemonDAO;

public class PokemonController {

    private static PokemonController instance;
    private static PokemonDAO pokemonDAO;

    public static PokemonController getInstance(boolean isTesting){
        if(instance == null){
            instance = new PokemonController();
            pokemonDAO = PokemonDAO.getInstance(isTesting);
        }
        return instance;
    }



}
