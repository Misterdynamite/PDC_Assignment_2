package Core.Database;

import Core.Player.Journey;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database extends Abstracts.Database.Database {

    protected DatabaseReader reader;
    protected DatabaseWriter writer;
    protected DatabaseConnection connection;
    protected DatabaseCreation creation;

    private static Database db;

    protected Database() throws SQLException {
        super();
        this.url = "jdbc:derby:CYOADatabase;create=true";
        this.user = "DBAccess";
        this.password = "ReallyCoolAdmin42";
        this.connection = new DatabaseConnection(url, user, password);
        this.reader = new DatabaseReader(this);
        this.writer = new DatabaseWriter(this);
        this.creation = new DatabaseCreation(this);

        creation.initialiseDatabase();
    }

    public static Database getInstance() throws SQLException {
        if (db == null) {
            db = new Database();
        }
        return db;
    }

    public Journey loadJourneyFromIndex(int index) {
        return this.reader.loadJourneyFromIndex(index);
    }

    public void saveJourney(Journey journey) {
        this.writer.saveJourney(journey);
    }

    public ArrayList<String[][]> getAliveJourneysBrief() {
        return this.reader.getAliveJourneysBrief();
    }

    @Override
    public Statement getStatement() {
        return this.connection.getStatement();
    }
    @Override
    public Connection getConnection() {
        return this.connection.getConnection();
    }


}
