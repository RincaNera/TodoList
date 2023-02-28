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
            connection = DriverManager.getConnection(HOST, USER, PASSWORD);
            connection.setAutoCommit(false);
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

    /**
     * Commit les changements sur la base de données.
     */
    public void commit() {
        try {
            this.connection.commit();
            System.out.println("Successfully committed changes.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Rollback les changements sur la base de données.
     */
    public void rollback() {
        try {
            this.connection.rollback();
            System.out.println("Successfully rolled back changes.");
        } catch (SQLException ex) {
            System.out.println("Could not rollback changes!");
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
        System.out.println("Closing connection...");
    }
}
