package ar.com.hotel.dao;

import ar.com.hotel.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final Connection con;

    public UserDAO(Connection con) {
        this.con = con;
    }

    public void create(User user) {
        final String query = "INSERT INTO USER(USER, PASSWORD) VALUES(?, ?)";

        try (con) {
            PreparedStatement statement = con.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            try (statement) {
                statement.setString(1, user.getUser());
                statement.setString(2, user.getPassword());

                statement.execute();

                final ResultSet resultSet = statement.getGeneratedKeys();

                try (resultSet) {
                    while (resultSet.next()) {
                        user.setId(resultSet.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> read() {
        final String query = "SELECT ID, USER, PASSWORD FROM USER";
        List<User> usersList = new ArrayList<>();

        try (con) {
            final PreparedStatement statement = con.prepareStatement(query);

            try (statement) {
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try (resultSet) {
                    while (resultSet.next()) {
                        User row = new User(
                                resultSet.getInt("ID"),
                                resultSet.getString("USER"),
                                resultSet.getString("PASSWORD")
                        );

                        usersList.add(row);
                    }
                }
            }
            return usersList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> read(String keyword) {
        final String query = "SELECT ID, USER, PASSWORD FROM USER WHERE ID like '%" + keyword + "%' OR USER like '%" + keyword + "%' OR PASSWORD like '%" + keyword + "%'";
        List<User> usersList = new ArrayList<>();

        try (con) {
            final PreparedStatement statement = con.prepareStatement(query);

            try (statement) {
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try (resultSet) {
                    while (resultSet.next()) {
                        User row = new User(
                                resultSet.getInt("ID"),
                                resultSet.getString("USER"),
                                resultSet.getString("PASSWORD")
                        );

                        usersList.add(row);
                    }
                }
            }
            System.out.println(usersList);
            return usersList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int update(User user) {
        final String query = "UPDATE USER SET USER = ?, PASSWORD = ? WHERE ID = ?";

        try (con) {
            final PreparedStatement statement = con.prepareStatement(query);

            try (statement) {
                statement.setString(1, user.getUser());
                statement.setString(2, user.getPassword());
                statement.setInt(3, user.getId());

                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int delete(Integer id) {
        final String query = "DELETE FROM USER WHERE ID = ?";

        try (con) {
            final PreparedStatement statement = con.prepareStatement(query);

            try (statement) {
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
