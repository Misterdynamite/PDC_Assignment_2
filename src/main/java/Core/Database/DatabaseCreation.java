package Core.Database;

public class DatabaseCreation extends Abstracts.Database.DatabaseCreation {

    public DatabaseCreation(Database database) {
        super(database);
    }

    // Initialises the database by creating the necessary tables.
    public void initialiseDatabase() {
        this.createJourneyTable();
        this.createCharacterTable();
    }

    // Creates the journey table if it does not exist.
    public boolean createJourneyTable() {
        if (this.checkIfTableExists("journeys")) {
            return true;
        }
        String sql = "CREATE TABLE journeys (" +
                "journey_id INT PRIMARY KEY," +
                "saved_event VARCHAR(64)," +
                "journey_log CLOB NOT NULL" +
                ")";
        try {
            this.statement.execute(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // Creates the character table if it does not exist.
    public boolean createCharacterTable() {
        if (this.checkIfTableExists("characters")) {
            return true;
        }
        String sql = "CREATE TABLE characters (" +
                "journey_id INT PRIMARY KEY, " +
                "character_name VARCHAR(32) NOT NULL, " +
                "hp INT NOT NULL, " +
                "money INT NOT NULL, " +
                "inventory CLOB NOT NULL, " +
                "FOREIGN KEY (journey_id) REFERENCES journeys(journey_id)" +
                ")";
        try {
            this.statement.execute(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
