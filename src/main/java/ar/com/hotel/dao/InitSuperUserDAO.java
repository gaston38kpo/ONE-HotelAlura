package ar.com.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InitSuperUserDAO {

    private final Connection con;

    public InitSuperUserDAO(Connection con) {
        this.con = con;
    }

    public void createSuperUser() {
        final String query = """
                             INSERT INTO user (user, password)
                             SELECT * FROM (SELECT 'admin' AS user, 'admin' AS password) AS temp
                             WHERE NOT EXISTS (
                                 SELECT user FROM user WHERE user='admin' AND password="admin"
                             ) LIMIT 1;""";

        try {
            PreparedStatement statement = con.prepareStatement(query);

            try (statement) {
                statement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
