package ar.com.hotel.dao;

import ar.com.hotel.model.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    private final Connection con;

    public ReservationDAO(Connection con) {
        this.con = con;
    }

    public void create(Reservation reservation) {
        final String query = "INSERT INTO RESERVATION(ENTRY_DATE, EXIT_DATE, VALUE, PAYMENT_METHOD) VALUES(?, ?, ?, ?)";

        try (con) {
            PreparedStatement statement = con.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);

            try (statement) {
                statement.setDate(1, reservation.getEntryDate());
                statement.setDate(2, reservation.getExitDate());
                statement.setInt(3, reservation.getValue());
                statement.setString(4, reservation.getPaymentMethod());

                statement.execute();

                final ResultSet resultSet = statement.getGeneratedKeys();

                try (resultSet) {
                    while (resultSet.next()) {
                        reservation.setId(resultSet.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Reservation> read() {
        final String query = "SELECT ENTRY_DATE, EXIT_DATE, VALUE, PAYMENT_METHOD FROM RESERVATION";
        List<Reservation> reservationsList = new ArrayList<>();

        try (con) {
            final PreparedStatement statement = con.prepareStatement(query);

            try (statement) {
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try (resultSet) {
                    while (resultSet.next()) {
                        Reservation row = new Reservation(
                                resultSet.getInt("ID"),
                                resultSet.getDate("ENTRY_DATE"),
                                resultSet.getDate("EXIT_DATE"),
                                resultSet.getInt("VALUE"),
                                resultSet.getString("PAYMENT_METHOD")
                        );

                        reservationsList.add(row);
                    }
                }
            }
            return reservationsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int update(Reservation reservation) {
        final String query = "UPDATE RESERVATION SET ENTRY_DATE = ?, EXIT_DATE = ?, VALUE = ?, PAYMENT_METHOD = ? WHERE ID = ?";

        try (con) {
            final PreparedStatement statement = con
                    .prepareStatement(query);

            try (statement) {
                statement.setDate(1, reservation.getEntryDate());
                statement.setDate(2, reservation.getExitDate());
                statement.setInt(3, reservation.getValue());
                statement.setString(4, reservation.getPaymentMethod());
                statement.setInt(6, reservation.getId());

                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int delete(Integer id) {
        final String query = "DELETE FROM RESERVATION WHERE ID = ?";

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
