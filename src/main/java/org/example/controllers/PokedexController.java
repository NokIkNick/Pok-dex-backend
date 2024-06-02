package org.example.controllers;

import io.javalin.http.Handler;
import jakarta.persistence.EntityNotFoundException;
import org.example.daos.PokedexDAO;
import org.example.dtos.PokedexDTO;
import org.example.exceptions.ApiException;
import org.example.persistence.Pokedex;
import org.example.utils.Updater;

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
            PokedexDTO toReturn = new PokedexDTO(pokedex);
            ctx.json(toReturn);
        });
    }

    public Handler updatePokedexById() {
        return (ctx -> {
            String pathParam = ctx.pathParam("id");
            PokedexDTO toUpdateWithDTO = ctx.bodyAsClass(PokedexDTO.class);
            int id;
            try{
                id = Integer.parseInt(pathParam);
            }catch(Exception e){
                throw new EntityNotFoundException("Error while updating pokedex: "+e.getMessage());
            }
            Pokedex toUpdateFrom = pokedexDAO.getById(id);
            if(toUpdateFrom == null){
                throw new ApiException(500, "Error while updating pokedex");
            }
            Pokedex toUpdate = Updater.updateFromDTO(toUpdateFrom, toUpdateWithDTO);
            Pokedex updated = pokedexDAO.update(toUpdate, id);
            PokedexDTO toReturn = new PokedexDTO(updated);
            ctx.json(toReturn);
        });
    }
}
