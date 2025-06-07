package Core.Database;

import Core.Player.Player;
import Core.Player.Journey;

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

    public ArrayList<String[][]> getAliveJourneysBrief()
    {
        ArrayList<String[][]> journeys = new ArrayList<>();
        try {
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
            ResultSet journeyResult = statement.executeQuery(
                    "SELECT * FROM journeys WHERE journey_id = " + index);

            if (!journeyResult.next()) return null;

            int journeyId = journeyResult.getInt("journey_id");
            Clob journeyLogClob = journeyResult.getClob("journey_log");
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

            return journey;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
