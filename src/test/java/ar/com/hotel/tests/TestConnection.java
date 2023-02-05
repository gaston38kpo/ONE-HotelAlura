package ar.com.hotel.tests;

import ar.com.hotel.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {

    public static void main(String[] args) throws SQLException {
        Connection con = new ConnectionFactory().getConnection();

        System.out.println("Cerrando la conexion");

        con.close();
    }
}
