package Abstracts.Database;

import java.sql.Connection;
import java.sql.Statement;

public abstract class Database {
    protected String url;
    protected String user;
    protected String password;

    protected java.sql.Connection connection;
    protected Statement statement;
    protected DatabaseReader reader;
    protected DatabaseWriter writer;


    public Database() {
    }

    public abstract void connect();
    public abstract void disconnect();
    public Statement getStatement(){return this.statement;}
    public Connection getConnection() {return this.connection;}


}
