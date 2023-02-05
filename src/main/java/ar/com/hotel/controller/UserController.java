package ar.com.hotel.controller;

import ar.com.hotel.dao.UserDAO;
import ar.com.hotel.factory.ConnectionFactory;
import ar.com.hotel.model.User;
import java.util.List;

public class UserController {

    private final UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO(new ConnectionFactory().getConnection());
    }

    public void create(User user) {
        userDAO.create(user);
    }

    public List<User> read() {
        return userDAO.read();
    }

    public int update(User user) {
        return userDAO.update(user);
    }

    public int delete(Integer id) {
        return userDAO.delete(id);
    }
}
