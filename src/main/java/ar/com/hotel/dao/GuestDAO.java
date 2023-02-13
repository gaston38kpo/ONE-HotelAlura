package ar.com.hotel.dao;

import ar.com.hotel.model.Guest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GuestDAO {

    private final Connection con;

    public GuestDAO(Connection con) {
        this.con = con;
    }

    public Guest create(Guest guest) {
        final String query = "INSERT INTO GUEST(NAME, LASTNAME, BIRTHDATE, NATIONALITY, PHONE, RESERVATION_ID) VALUES(?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = con.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            try (statement) {
                statement.setString(1, guest.getName());
                statement.setString(2, guest.getLastname());
                statement.setDate(3, guest.getBirthdate());
                statement.setString(4, guest.getNationality());
                statement.setString(5, guest.getPhone());
                statement.setInt(6, guest.getReservationID());

                statement.execute();

                final ResultSet resultSet = statement.getGeneratedKeys();

                try (resultSet) {
                    while (resultSet.next()) {
                        guest.setId(resultSet.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return guest;
    }

    public List<Guest> read() {
        final String query = "SELECT ID, NAME, LASTNAME, BIRTHDATE, NATIONALITY, PHONE FROM GUEST";
        List<Guest> guestsList = new ArrayList<>();

        try {
            final PreparedStatement statement = con.prepareStatement(query);

            try (statement) {
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try (resultSet) {
                    while (resultSet.next()) {
                        Guest row = new Guest(
                                resultSet.getInt("ID"),
                                resultSet.getString("NAME"),
                                resultSet.getString("LASTNAME"),
                                resultSet.getDate("BIRTHDATE"),
                                resultSet.getString("NATIONALITY"),
                                resultSet.getString("PHONE")
                        );

                        guestsList.add(row);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return guestsList;
    }

    public List<Guest> read(String keyword) {
        final String query = "SELECT ID, NAME, LASTNAME, BIRTHDATE, NATIONALITY, PHONE FROM GUEST WHERE "
                + "ID like '%" + keyword + "%' OR "
                + "NAME like '%" + keyword + "%' OR "
                + "LASTNAME like '%" + keyword + "%' OR "
                + "BIRTHDATE like '%" + keyword + "%' OR "
                + "NATIONALITY like '%" + keyword + "%' OR "
                + "PHONE like '%" + keyword + "%'";
        List<Guest> guestsList = new ArrayList<>();

        try {
            final PreparedStatement statement = con.prepareStatement(query);

            try (statement) {
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try (resultSet) {
                    while (resultSet.next()) {
                        Guest row = new Guest(
                                resultSet.getInt("ID"),
                                resultSet.getString("NAME"),
                                resultSet.getString("LASTNAME"),
                                resultSet.getDate("BIRTHDATE"),
                                resultSet.getString("NATIONALITY"),
                                resultSet.getString("PHONE")
                        );

                        guestsList.add(row);
                    }
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return guestsList;
    }

    public int update(Guest guest) {
        final String query = "UPDATE GUEST SET NAME = ?, LASTNAME = ?, BIRTHDATE = ?, NATIONALITY = ?, PHONE = ? WHERE ID = ?";
        int updateCount = 0;

        try {
            final PreparedStatement statement = con
                    .prepareStatement(query);

            try (statement) {
                statement.setString(1, guest.getName());
                statement.setString(2, guest.getLastname());
                statement.setDate(3, guest.getBirthdate());
                statement.setString(4, guest.getNationality());
                statement.setString(5, guest.getPhone());
                statement.setInt(6, guest.getId());

                statement.execute();

                updateCount = statement.getUpdateCount();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return updateCount;
    }

    public int delete(Integer id) {
        final String query = "DELETE FROM GUEST WHERE ID = ?";
        int deleteCount = 0;

        try {
            final PreparedStatement statement = con.prepareStatement(query);

            try (statement) {
                statement.setInt(1, id);
                statement.execute();

                deleteCount = statement.getUpdateCount();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return deleteCount;
    }

}
