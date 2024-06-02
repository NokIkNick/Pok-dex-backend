package org.example;

import org.example.config.ApplicationConfig;
import org.example.config.Routes;
import org.example.utils.Populator;


public class Main {
    public static void main(String[] args) {
        boolean isTesting = false;
        ApplicationConfig app = ApplicationConfig.getInstance()
                .initiateServer()
                .setExceptionHandling()
                .startServer(7070)
                .setRoutes(Routes.getRoutes(isTesting))
                .checkSecurityRoles(isTesting)
                .configureCors();
        //Populator.populate(isTesting);


    }
}