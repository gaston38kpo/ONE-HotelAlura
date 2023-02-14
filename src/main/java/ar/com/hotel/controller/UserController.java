package ar.com.hotel.controller;

import ar.com.hotel.dao.UserDAO;
import ar.com.hotel.model.User;
import java.util.List;

public class UserController {

    private final UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO(ConnectionController.connection);
    }

    public void create(User user) {
        userDAO.create(user);
    }

    public List<User> read() {
        return userDAO.read();
    }

    public List<User> read(String keyword) {
        return userDAO.read(keyword);
    }

    public boolean isUserAndPasswordInDB(String userInput, String passwordInput) {
        List<User> usersList = this.read();

        for (User user : usersList) {
            if (user.getUser().equals(userInput) && user.getPassword().equals(passwordInput)) {
                return true;
            }
        }

        return false;
    }

    public boolean isUserInDB(String userInput) {
        List<User> usersList = this.read();

        for (User user : usersList) {
            if (user.getUser().equals(userInput)) {
                return true;
            }
        }

        return false;
    }

    public int update(User user) {
        return userDAO.update(user);
    }

    public int delete(Integer id) {
        return userDAO.delete(id);
    }
}
