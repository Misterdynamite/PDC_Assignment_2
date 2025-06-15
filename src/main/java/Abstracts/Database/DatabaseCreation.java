package Abstracts.Database;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class DatabaseCreation {
    protected Database database;
    protected Statement statement;

    public DatabaseCreation(Database database) {
        this.database = database;
        this.statement = this.database.getStatement();
    }
    // Check if a table exists in the database
    public boolean checkIfTableExists(String tableName) {
        try {
            DatabaseMetaData meta = this.database.getConnection().getMetaData();
            try (ResultSet rs = meta.getTables(null, null, tableName.toUpperCase(), null)) {
                return rs.next();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to check if table exists: " + tableName, e);
        }
    }


}
