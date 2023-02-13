package ar.com.hotel.dao;

import ar.com.hotel.model.Reservation;
import java.sql.Connection;
import java.sql.Date;
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

    public Reservation create(Reservation reservation) {
        final String query = "INSERT INTO RESERVATION(ENTRY_DATE, EXIT_DATE, VALUE, PAYMENT_METHOD) VALUES(?, ?, ?, ?)";

        try {
            PreparedStatement statement = con.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);

            try (statement) {
                statement.setDate(1, (java.sql.Date) reservation.getEntryDate());
                statement.setDate(2, (java.sql.Date) reservation.getExitDate());
                statement.setBigDecimal(3, reservation.getValue());
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

        return reservation;
    }

    public List<Reservation> read() {
        final String query = "SELECT ID, ENTRY_DATE, EXIT_DATE, VALUE, PAYMENT_METHOD FROM RESERVATION";
        List<Reservation> reservationsList = new ArrayList<>();

        try {
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
                                resultSet.getBigDecimal("VALUE"),
                                resultSet.getString("PAYMENT_METHOD")
                        );

                        reservationsList.add(row);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reservationsList;
    }

    public List<Reservation> read(String keyword) {
        final String query = "SELECT ID, ENTRY_DATE, EXIT_DATE, VALUE, PAYMENT_METHOD FROM RESERVATION WHERE "
                + "ID like '%" + keyword + "%' OR "
                + "ENTRY_DATE like '%" + keyword + "%' OR "
                + "EXIT_DATE like '%" + keyword + "%' OR "
                + "VALUE like '%" + keyword + "%' OR "
                + "PAYMENT_METHOD like '%" + keyword + "%'";

        List<Reservation> reservationsList = new ArrayList<>();

        try {
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
                                resultSet.getBigDecimal("VALUE"),
                                resultSet.getString("PAYMENT_METHOD")
                        );

                        reservationsList.add(row);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reservationsList;
    }

    public int update(Reservation reservation) {
        final String query = "UPDATE RESERVATION SET ENTRY_DATE = ?, EXIT_DATE = ?, VALUE = ?, PAYMENT_METHOD = ? WHERE ID = ?";
        int updateCount = 0;
        try {
            final PreparedStatement statement = con
                    .prepareStatement(query);

            try (statement) {
                statement.setDate(1, (Date) reservation.getEntryDate());
                statement.setDate(2, (Date) reservation.getExitDate());
                statement.setBigDecimal(3, reservation.getValue());
                statement.setString(4, reservation.getPaymentMethod());
                statement.setInt(5, reservation.getId());

                statement.execute();

                updateCount = statement.getUpdateCount();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return updateCount;
    }

    public int delete(Integer id) {
        final String query = "DELETE FROM RESERVATION WHERE ID = ?";
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
