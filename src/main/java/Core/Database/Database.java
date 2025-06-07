package Core.Database;

import Core.Player.Journey;

import java.sql.SQLException;
import java.util.ArrayList;

public class Database extends Abstracts.Database.Database {

    protected DatabaseReader reader;
    protected DatabaseWriter writer;

    public Database() {
        super();
        this.url = "jdbc:derby:CYOADatabase;create=true";
        this.user = "DBAccess";
        this.password = "ReallyCoolAdmin42";
        this.connect();
        this.reader = new DatabaseReader(this);
        this.writer = new DatabaseWriter(this);
        this.initialiseDatabase();
    }

    @Override
    public void connect() {
        try {
            this.connection = java.sql.DriverManager.getConnection(url, user, password);
            this.statement = this.connection.createStatement();
            

        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to embedded database", e);
        }
    }

    public void initialiseDatabase() {
        DatabaseCreation creation = new DatabaseCreation(this);
        creation.initaliseDatabase();

    }

    @Override
    public void disconnect() {
        try {
            if (this.statement != null && !this.statement.isClosed()) {
                this.statement.close();
            }
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to close the database connection", e);
        }
    }

    public Journey loadJourneyFromIndex(int index){
        return this.reader.loadJourneyFromIndex(index);
    }

    public void saveJourney(Journey journey) {
        this.writer.saveJourney(journey);
    }

    public ArrayList<String[][]> getAliveJourneysBrief()
    {
        return this.reader.getAliveJourneysBrief();
    }
}
