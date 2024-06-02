package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.security.RouteRole;
import jakarta.persistence.EntityManagerFactory;
import org.example.controllers.PokedexController;
import org.example.controllers.PokemonController;
import org.example.controllers.SecurityController;
import org.example.controllers.TestController;
import org.example.daos.TestMemoryDao;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Routes {
    private static SecurityController sc;
    private static PokedexController pdc;
    private static PokemonController pc;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static TestController tc;

    public static EndpointGroup getRoutes(boolean isTesting){
        sc = SecurityController.getInstance(isTesting);
        pdc = PokedexController.getInstance(isTesting);
        pc = PokemonController.getInstance(isTesting);
        return () -> {
            path("/", () -> {
                get("/", ctx -> ctx.json(objectMapper.createObjectNode().put("Message", "Connected Successfully")), roles.ANYONE);
            });
            path("/auth", () -> {
                post("/login", sc.login(), roles.ANYONE);
                post("/register", sc.register(), roles.ANYONE);
            });
            path("/protected", () -> {
                before(sc.authenticate());
                get("/getPokedexByUserId/{id}", pdc.getPokedexByUserId(), roles.USER);
                put("/updatePokedexById/{id}", pdc.updatePokedexById(), roles.USER);
            });
        };
    }

    public static EndpointGroup getTestRoutes(){
        tc = TestController.getInstance();
        return ()-> {
            get("/getAll", tc.getAllTest());
            post("/create",tc.createTest());
        };
    }


    public enum roles implements RouteRole {
        USER,
        ADMIN,
        ANYONE
    }

}
