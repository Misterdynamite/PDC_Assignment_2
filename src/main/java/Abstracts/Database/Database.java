package Abstracts.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Database {
    protected String url;
    protected String user;
    protected String password;

    protected DatabaseReader reader;
    protected DatabaseWriter writer;
    protected DatabaseConnection connection;

    public Database() {
    }

    // Get statement from the database connection
    public Statement getStatement() {
        return this.connection.getStatement();
    }
    // Get connection
    public Connection getConnection() {
        return this.connection.getConnection();
    }


}
