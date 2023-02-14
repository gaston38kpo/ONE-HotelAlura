package ar.com.hotel.controller;

import ar.com.hotel.factory.ConnectionFactory;
import java.sql.Connection;

public class ConnectionController {

    public static Connection connection = new ConnectionFactory().getConnection();
}
