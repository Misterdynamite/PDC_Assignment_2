package Core.Database;

public class DatabaseReader extends Abstracts.Database.DatabaseReader {

    public DatabaseReader(Database database) {
        this.database = database;
        this.statement = database.getStatement();
    }


}
