package ar.com.hotel.controller;

import ar.com.hotel.dao.InitSuperUserDAO;

public class InitSuperUserController {

    private final InitSuperUserDAO initSuperUserDAO;

    public InitSuperUserController() {
        this.initSuperUserDAO = new InitSuperUserDAO(ConnectionController.connection);
    }

    public void createSuperUser() {
        initSuperUserDAO.createSuperUser();
    }
}
