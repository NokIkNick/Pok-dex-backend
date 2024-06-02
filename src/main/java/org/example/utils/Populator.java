package org.example.utils;

import jakarta.persistence.EntityManager;
import org.example.config.HibernateConfig;
import org.example.model.*;
import org.example.persistence.Move;
import org.example.persistence.Pokedex;
import org.example.persistence.Pokemon;

import java.util.HashSet;
import java.util.Set;

public class Populator {

    public static void populate(boolean isTest){
        EntityManager em = HibernateConfig.getEntityManagerFactoryConfig(isTest).createEntityManager();
        em.getTransaction().begin();

        try{



            //Initialize Moves:
            Move tackle = new Move();
            tackle.setName("Tackle");
            tackle.setPp(35);
            tackle.setDescription("A full-body charge attack.");
            tackle.setAccuracy(100);
            tackle.setPower(40);
            tackle.setMoveType(MoveType.PHYSICAL);
            tackle.setType(Type.NORMAL);
            em.persist(tackle);

            //Initialize Pokemon
            Pokemon bulbasaur = new Pokemon();
            bulbasaur.setDex_number("0001");
            bulbasaur.setName("Bulbasaur");
            bulbasaur.setDexEntry("A strange seed was planted on its back at birth. The plant sprouts and grows with this POKéMON");
            bulbasaur.setHp(45);
            bulbasaur.setAtk(49);
            bulbasaur.setDef(49);
            bulbasaur.setSpatk(65);
            bulbasaur.setSpdef(65);
            bulbasaur.setSpd(45);
            bulbasaur.setGender(Gender.MALE);
            bulbasaur.setHeight("0.7m");
            Set<Move> moves = new HashSet<>();
            moves.add(tackle);
            bulbasaur.setMoves(moves);
            bulbasaur.setShiny(false);
            bulbasaur.setWeight("6.9kg");
            bulbasaur.setTypeOne(Type.GRASS);
            bulbasaur.setTypeTwo(Type.POISON);
            bulbasaur.setSpriteUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png");
            bulbasaur.setShinySpriteUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/1.png");
            em.persist(bulbasaur);

            Pokemon ivysaur = new Pokemon();
            ivysaur.setDex_number("0002");
            ivysaur.setName("Ivysaur");
            ivysaur.setDexEntry("When the bulb on its back grows large, it appears to lose the ability to stand on its hind legs.");
            ivysaur.setHp(60);
            ivysaur.setAtk(62);
            ivysaur.setDef(63);
            ivysaur.setSpatk(80);
            ivysaur.setSpdef(80);
            ivysaur.setSpd(60);
            ivysaur.setGender(Gender.MALE);
            ivysaur.setHeight("1.0m");
            ivysaur.setMoves(moves);
            ivysaur.setShiny(false);
            ivysaur.setWeight("13.0kg");
            ivysaur.setTypeOne(Type.GRASS);
            ivysaur.setTypeTwo(Type.POISON);
            ivysaur.setSpriteUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png");
            ivysaur.setShinySpriteUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/2.png");
            em.persist(ivysaur);



            //Initialize Pokedex
            Pokedex pokedex = new Pokedex();
            Set<Pokemon> pokemon = new HashSet<>();
            pokemon.add(bulbasaur);
            pokedex.setPokemon(pokemon);
            em.persist(pokedex);

            //Initialize roles:
            Role userRole = new Role();
            Role adminRole = new Role();
            userRole.setName("USER");
            adminRole.setName("ADMIN");
            em.persist(userRole);
            em.persist(adminRole);

            //Initialize Users:
            User user = new User("regularUser", "1234", "regularUser@email.com");
            Set<Role> roles = new HashSet<>();
            roles.add(userRole);
            user.setRoles(roles);
            user.setPokedex(pokedex);
            em.persist(user);

            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }


    }

}
