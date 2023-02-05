package ar.com.hotel.tests;

import ar.com.hotel.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;

public class TestPoolConnections {

    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        for (int i = 0; i < 20; i++) {
            connectionFactory.getConnection();

            System.out.println("abriendo la conexion numero " + (i + 1));
        }
    }
}
