package ar.com.hotel.factory;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

    private final DataSource datasource;

    public ConnectionFactory() {
        ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
        pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel_alura?useTimeZone=true&serverTimeZone=UTC");
        pooledDataSource.setUser("root");
        pooledDataSource.setPassword("1992");
        pooledDataSource.setMinPoolSize(0);
        pooledDataSource.setInitialPoolSize(0);
        pooledDataSource.setAcquireIncrement(1);
        pooledDataSource.setMaxPoolSize(20);

        datasource = pooledDataSource;
    }

    public Connection getConnection() {
        try {
            return datasource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
