package Core.Database;

import Core.Player.Player;
import Core.Player.Journey;
import Core.Utilities.DatabaseUtilities;
import Core.Utilities.GameUtilities;

import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseReader extends Abstracts.Database.DatabaseReader {

    public DatabaseReader(Database database) {
        super();
        this.database = database;
        this.statement = database.getStatement();
    }

    public ArrayList<String[][]> getAliveJourneysBrief() {
        ArrayList<String[][]> journeys = new ArrayList<>();
        try {
            // Query to get all journeys with characters that are still alive (hp > 0)
            // Only returns brief data such as journey_id, character_name, hp, and money
            ResultSet resultSet = statement.executeQuery("SELECT journey_id, character_name, hp, money FROM characters WHERE hp > 0");

            while (resultSet.next()) {
                String[] characterKey = new String[1];
                String[] journeyData = new String[3];

                journeyData[0] = resultSet.getString("character_name");
                journeyData[1] = String.valueOf(resultSet.getInt("hp"));
                journeyData[2] = String.valueOf(resultSet.getInt("money"));
                characterKey[0] = String.valueOf(resultSet.getInt("journey_id"));
                journeys.add(new String[][]{characterKey, journeyData});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return journeys;
    }

    public Journey loadJourneyFromIndex(int index) {
        try {
            // Query and parsing logic to load a journey by its index in the database
            ResultSet journeyResult = statement.executeQuery(
                    "SELECT * FROM journeys WHERE journey_id = " + index);

            if (!journeyResult.next()) return null;

            int journeyId = journeyResult.getInt("journey_id");
            // Retrieve the CLOB containing the journey log and the current event
            Clob journeyLogClob = journeyResult.getClob("journey_log");
            // Get the current event from the journey
            String currentEvent = journeyResult.getString("saved_event");
            ArrayList<String> journeyLog = DatabaseUtilities.clobToJourneyLog(journeyLogClob);

            ResultSet characterResult = statement.executeQuery(
                    "SELECT * FROM characters WHERE journey_id = " + journeyId);

            if (!characterResult.next()) return null;

            Player player = new Player(characterResult.getString("character_name"));
            player.setHealth(characterResult.getInt("hp"));
            player.setMoney(characterResult.getInt("money"));
            player.getInventory().setInventory(
                    DatabaseUtilities.clobToInventory(characterResult.getClob("inventory")));
            Journey journey = new Journey(player);
            journey.setJourneyId(journeyId);
            journey.setJourneyLog(journeyLog);
            // Load the event using the loadEvent method from GameUtilities
            journey.setCurrentEvent(GameUtilities.loadEvent(currentEvent, player));

            return journey;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load journey from index: " + index, e);
        }
    }
}
