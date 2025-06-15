package Abstracts.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DatabaseConnection {
    private final String url;
    private final String user;
    private final String password;
    private Connection connection;
    private Statement statement;

    // Constructor to initialize the database's connection
    public DatabaseConnection(String url, String user, String password) throws SQLException {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connect();
    }

    // Establish a connection to the database
    public void connect() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(url, user, password);
            this.statement = this.connection.createStatement();
        } catch (SQLException e) {
            throw new SQLException("Failed to connect to the database", e);
        }
    }

    // Close the database connection and statement
    public void disconnect() throws SQLException {
        try {
            if (this.statement != null && !this.statement.isClosed()) {
                this.statement.close();
            }
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to close the database connection", e);
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public Statement getStatement() {
        return this.statement;
    }
}