package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

    public DbConnection() {
        try {
            createDatabaseAndTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createDatabaseAndTable() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/exampledb";
        String user = "root";
        String password = "vibbeh";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            System.out.println("Connected to the database!");

            // Create the database
            String createDatabase = "CREATE DATABASE IF NOT EXISTS exampledb";
            stmt.executeUpdate(createDatabase);

            // Connect to the new database
            conn.setCatalog("exampledb");

            // Create the table
            String createTable = "CREATE TABLE IF NOT EXISTS Books (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "title VARCHAR(255), " +
                    "author VARCHAR(255), " +
                    "price DOUBLE)";
            stmt.executeUpdate(createTable);

            System.out.println("Database and table created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
