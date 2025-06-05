package Core.Database;

public class DatabaseCreation extends Abstracts.Database.DatabaseCreation {

    public DatabaseCreation(Database database) {
        super(database);
    }

    public boolean createJourneyTable() {
        if (this.checkIfTableExists("journey")) {
            return true;
        }
        String sql = "CREATE TABLE journey (" +
                "journey_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                "character_id INT NOT NULL, " +
                "character_data CLOB NOT NULL, " +
                "journey_log CLOB NOT NULL, " +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (character_id) REFERENCES character(character_id)" +
                ")";
        try {
            this.statement.execute(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean createCharacterTable() {
        if (this.checkIfTableExists("character")) {
            return true;
        }
        String sql = "CREATE TABLE character (" +
                "character_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                "character_name VARCHAR(32) NOT NULL UNIQUE" +
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
