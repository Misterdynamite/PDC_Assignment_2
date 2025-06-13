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


    public Statement getStatement() {
        return this.connection.getStatement();
    }

    public Connection getConnection() {
        return this.connection.getConnection();
    }


}
