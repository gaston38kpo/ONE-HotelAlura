package ar.com.hotel.tests;

import ar.com.hotel.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {

    public static void main(String[] args) throws SQLException {
        for (int i = 0; i < 10; i++) {
            Connection con = new ConnectionFactory().getConnection();           

            con.close();
        }
        
        for (int i = 0; i < 1000000; i++) {
            System.out.println("Cerrando la conexion");
        }
        
    }
}
