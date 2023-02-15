package ar.com.hotel.tests;

import ar.com.hotel.factory.ConnectionFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.util.Scanner;


public class TestPoolConnections {

    public static void main(String[] args) throws SQLException {
        /*ConnectionFactory connectionFactory = new ConnectionFactory();

        for (int i = 0; i < 20; i++) {
            connectionFactory.getConnection();

            System.out.println("abriendo la conexion numero " + (i + 1));
        }*/

        ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
        pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel_alura?useTimeZone=true&serverTimeZone=UTC");
        pooledDataSource.setUser("root");
        pooledDataSource.setPassword("1992");
        pooledDataSource.setMinPoolSize(0);
        pooledDataSource.setInitialPoolSize(0);
        pooledDataSource.setAcquireIncrement(1);

        DataSource datasource = pooledDataSource;
        datasource.getConnection();

        Scanner sc = new Scanner(System.in);
        String Inpt = sc.next();
        System.out.println(Inpt);

    }
}
