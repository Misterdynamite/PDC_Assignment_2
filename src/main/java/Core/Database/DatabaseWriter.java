package Core.Database;

public class DatabaseWriter extends Abstracts.Database.DatabaseWriter {
    public DatabaseWriter(Database database) {
        this.database = database;
        this.statement = database.getStatement();
    }
}
