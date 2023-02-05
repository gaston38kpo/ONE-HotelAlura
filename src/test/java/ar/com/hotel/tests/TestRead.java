package ar.com.hotel.tests;

import ar.com.hotel.controller.UserController;
import ar.com.hotel.model.User;
import java.sql.SQLException;
import java.util.List;

public class TestRead {

    public static void main(String[] args) throws SQLException {
        List<User> users = new UserController().read();

        for (User user : users) {
            System.out.println(user.getUser());
        }
    }
}
