package Core.Database;

import Abstracts.Database.Database;

public class DatabaseDestruction extends Abstracts.Database.DatabaseDestruction {

    public DatabaseDestruction(Database database) {
        super(database);
    }


    // This method is used to truncate table data.
    // Only used in unit tests.
    public void truncateTables()
    {
        try {
            statement.executeUpdate("DELETE FROM characters");
            statement.executeUpdate("DELETE FROM journeys");
        } catch (Exception e) {
            throw new RuntimeException("Failed to truncate tables", e);
        }
    }
    // This method is used to drop tables.
    // Only used in unit tests.
    public void dropTables()
    {
        try {
            statement.executeUpdate("DROP TABLE characters");
            statement.executeUpdate("DROP TABLE journeys");
        } catch (Exception e) {
            throw new RuntimeException("Failed to drop tables", e);
        }
    }


}
