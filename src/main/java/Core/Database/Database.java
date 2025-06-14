package Core.Database;

import Abstracts.Logic.Event;
import Core.Player.Journey;

import java.sql.SQLException;
import java.util.List;

public class Database extends Abstracts.Database.Database {

    protected DatabaseReader reader;
    protected DatabaseWriter writer;

    public Database() throws SQLException {
        super();
        this.url = "jdbc:derby:CYOADatabase;create=true";
        this.user = "DBAccess";
        this.password = "ReallyCoolAdmin42";
        this.reader = new DatabaseReader(this);
        this.writer = new DatabaseWriter(this);
        this.connection = new DatabaseConnection(url, user, password);
        DatabaseCreation creation = new DatabaseCreation(this);
        creation.initaliseDatabase();
    }

    public Journey loadJourneyFromIndex(int index) {
        return this.reader.loadJourneyFromIndex(index);
    }

    public void saveJourney(Journey journey) {
        this.writer.saveJourney(journey);
    }

    public List<String[][]> getAliveJourneysBrief() {
        return this.reader.getAliveJourneysBrief();
    }
}
