package Abstracts.Database;

import Core.Player.Journey;

import java.sql.Clob;
import java.sql.Statement;

public abstract class DatabaseReader {
    protected Database database;
    protected Statement statement;

    public DatabaseReader() {
    }

}
