package Abstracts.Database;

import java.sql.Statement;

public abstract class DatabaseWriter {
    protected Database database;
    protected Statement statement;

    public DatabaseWriter() {
    }
}
