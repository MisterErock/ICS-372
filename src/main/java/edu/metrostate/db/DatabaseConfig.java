package edu.metrostate.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    public static String databaseName = "database.db";
    public static String connectionString;

    public DatabaseConfig() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString);
    }

    static {
        connectionString = "jdbc:sqlite:" + databaseName;
    }
}
