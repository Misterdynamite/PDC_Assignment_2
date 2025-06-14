package Abstracts.Database;

import java.sql.Statement;

public abstract class DatabaseDestruction {
    protected Database database;
    protected Statement statement;

    public DatabaseDestruction(Database database) {
        this.database = database;
        this.statement = this.database.getStatement();
    }



}
