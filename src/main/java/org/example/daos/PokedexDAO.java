package org.example.daos;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.exceptions.ApiException;
import org.example.persistence.Pokedex;

import javax.management.Query;

@Getter
@Setter
public class PokedexDAO extends DAO<Pokedex, Integer>{

    private static PokedexDAO instance;
    private ObjectMapper objectMapper;

    private PokedexDAO (boolean isTesting){
        super(Pokedex.class, isTesting);
    }

    public static PokedexDAO getInstance(boolean isTesting){
        if(instance == null){
            instance = new PokedexDAO(isTesting);
        }
        return instance;
    }


    public Pokedex getPokedexByUserId(String id) throws ApiException {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            Pokedex result;
            TypedQuery<Pokedex> query = em.createQuery("SELECT p FROM Pokedex p WHERE p.user.id = :id ", Pokedex.class);
            query.setParameter("id", id);
            result = query.getSingleResult();
            return result;
        }catch (Exception e){
            throw new ApiException(500, "Error while getting pokedex by userId: "+id +", "+e.getMessage());
        }
    }
}
