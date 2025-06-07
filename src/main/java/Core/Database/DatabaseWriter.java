package Core.Database;

import Core.Player.Journey;
import Core.Player.Player;

import java.sql.SQLException;

public class DatabaseWriter extends Abstracts.Database.DatabaseWriter {
    public DatabaseWriter(Database database) {
        super();
        this.database = database;
        this.statement = database.getStatement();
    }


    public void saveJourney(Journey journey) {
        try {
            Player player = (Player) journey.getPlayer();
            int journeyId = journey.getJourneyId();

            if (journeyId == 0) {
                try (java.sql.ResultSet rs = statement.executeQuery("SELECT COALESCE(MAX(journey_id), 0) + 1 AS next_id FROM journeys")) {
                    if (rs.next()) {
                        journeyId = rs.getInt("next_id");
                    }
                }
                journey.setJourneyId(journeyId);
            }

            String updateJourneySql = "UPDATE journeys SET journey_log = ? WHERE journey_id = ?";
            try (java.sql.PreparedStatement journeyPs = database.getConnection().prepareStatement(updateJourneySql)) {
                journeyPs.setClob(1, DatabaseUtilities.journeyLogToClob(journey.getJourneyLog()));
                journeyPs.setInt(2, journeyId);
                if (journeyPs.executeUpdate() == 0) {
                    String insertJourneySql = "INSERT INTO journeys (journey_id, journey_log) VALUES (?, ?)";
                    try (java.sql.PreparedStatement insertJourneyPs = database.getConnection().prepareStatement(insertJourneySql)) {
                        insertJourneyPs.setInt(1, journeyId);
                        insertJourneyPs.setClob(2, DatabaseUtilities.journeyLogToClob(journey.getJourneyLog()));
                        insertJourneyPs.executeUpdate();
                    }
                }
            }

            String updateCharacterSql = "UPDATE characters SET character_name = ?, hp = ?, money = ?, inventory = ? WHERE journey_id = ?";
            try (java.sql.PreparedStatement characterPs = database.getConnection().prepareStatement(updateCharacterSql)) {
                characterPs.setString(1, player.getCharacterName());
                characterPs.setInt(2, player.getHealth());
                characterPs.setInt(3, player.getMoney());
                characterPs.setClob(4, DatabaseUtilities.inventoryToClob(player.getInventory().getInventory()));
                characterPs.setInt(5, journeyId);
                if (characterPs.executeUpdate() == 0) {
                    String insertCharacterSql = "INSERT INTO characters (journey_id, character_name, hp, money, inventory) VALUES (?, ?, ?, ?, ?)";
                    try (java.sql.PreparedStatement insertCharacterPs = database.getConnection().prepareStatement(insertCharacterSql)) {
                        insertCharacterPs.setInt(1, journeyId);
                        insertCharacterPs.setString(2, player.getCharacterName());
                        insertCharacterPs.setInt(3, player.getHealth());
                        insertCharacterPs.setInt(4, player.getMoney());
                        insertCharacterPs.setClob(5, DatabaseUtilities.inventoryToClob(player.getInventory().getInventory()));
                        insertCharacterPs.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
