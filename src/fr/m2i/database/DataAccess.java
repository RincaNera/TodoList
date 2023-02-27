package fr.m2i.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataAccess implements AutoCloseable{

    private static final String HOST = "jdbc:mysql://localhost:3306/todo";
    private static final String USER = "root";
    private static final String PASSWORD = "toto1234";

    private Connection connection = null;

    private static DataAccess instance = null;

    public Connection getConnection() {
        return connection;
    }

    private DataAccess() {
        createConnection();
    }

    private void createConnection() {
        try {
            this.connection = DriverManager.getConnection(HOST, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DataAccess getInstance() {
        if (instance == null) {
            instance = new DataAccess();
        }
        return instance;
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing connection...");
    }
}
