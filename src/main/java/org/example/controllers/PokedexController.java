package org.example.controllers;

import io.javalin.http.Handler;
import jakarta.persistence.EntityNotFoundException;
import org.example.daos.PokedexDAO;
import org.example.dtos.PokedexDTO;
import org.example.exceptions.ApiException;
import org.example.persistence.Pokedex;
import org.example.persistence.Pokemon;
import org.example.utils.Updater;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class PokedexController {

    private static PokedexController instance;
    private static PokedexDAO pokedexDAO;

    private PokedexController(){

    }

    public static PokedexController getInstance(boolean isTesting){
        if(instance == null){
            instance = new PokedexController();
            pokedexDAO = PokedexDAO.getInstance(isTesting);
        }
        return instance;
    }

    public Handler getPokedexByUserId(){
        return (ctx -> {
            String id = ctx.pathParam("id");
            Pokedex pokedex = pokedexDAO.getPokedexByUserId(id);
            pokedex.setPokemon(pokedex.getPokemon().stream().sorted(Comparator.comparingInt((x) -> Integer.parseInt(x.getDex_number()))).collect(Collectors.toCollection(LinkedHashSet::new)));
            PokedexDTO toReturn = new PokedexDTO(pokedex);
            ctx.json(toReturn);
        });
    }

    public Handler updatePokedexById() {
        return (ctx -> {
            String pathParam = ctx.pathParam("id");
            PokedexDTO body = ctx.bodyAsClass(PokedexDTO.class);
            int id;
            try{
                id = Integer.parseInt(pathParam);
            }catch(Exception e){
                throw new EntityNotFoundException("Error while updating pokedex: "+e.getMessage());
            }
            Pokedex found = pokedexDAO.getById(id);
            if(found == null){
                throw new ApiException(500, "Error while updating pokedex");
            }
            found.setPokemon(body.getPokemon().stream().map(Pokemon::new).collect(Collectors.toSet()));
            Pokedex updated = pokedexDAO.update(found, id);
            PokedexDTO toReturn = new PokedexDTO(updated);
            ctx.json(toReturn);
        });
    }
}
