package Core.Database;

import Core.Player.Journey;
import Core.Player.Player;
import Core.Utilities.DatabaseUtilities;

import java.sql.SQLException;

public class DatabaseWriter extends Abstracts.Database.DatabaseWriter {
    public DatabaseWriter(Database database) {
        super();
        this.database = database;
        this.statement = database.getStatement();
    }

    // Saves the journey and character data to the database
    public void saveJourney(Journey journey) {
        try {
            Player player = (Player) journey.getPlayer();
            int journeyId = journey.getJourneyId();
            // If an ID hasn't been set (ie a new game), generate a new one and assign to the journey.
            // Important as this is what the database uses to identify the journey and character.
            if (journeyId == 0) {
                try (java.sql.ResultSet rs = statement.executeQuery("SELECT COALESCE(MAX(journey_id), 0) + 1 AS next_id FROM journeys")) {
                    if (rs.next()) {
                        journeyId = rs.getInt("next_id");
                    }
                }
                journey.setJourneyId(journeyId);
            }
            // Update the journey data
            String updateJourneySql = "UPDATE journeys SET journey_log = ?, saved_event = ? WHERE journey_id = ?";
            try (java.sql.PreparedStatement journeyPs = database.getConnection().prepareStatement(updateJourneySql)) {
                journeyPs.setClob(1, DatabaseUtilities.journeyLogToClob(journey.getJourneyLog()));
                journeyPs.setString(2, journey.getCurrentEvent().getClass().getSimpleName());
                journeyPs.setInt(3, journeyId);
                if (journeyPs.executeUpdate() == 0) {
                    // If no rows were updated, insert a new journey record
                    String insertJourneySql = "INSERT INTO journeys (journey_id, journey_log, saved_event) VALUES (?, ?, ?)";
                    try (java.sql.PreparedStatement insertJourneyPs = database.getConnection().prepareStatement(insertJourneySql)) {
                        insertJourneyPs.setInt(1, journeyId);
                        // Convert the journey log to a CLOB and set it in the statement
                        insertJourneyPs.setClob(2, DatabaseUtilities.journeyLogToClob(journey.getJourneyLog()));
                        // Use the class name of the current event as a string
                        insertJourneyPs.setString(3, journey.getCurrentEvent().getClass().getSimpleName());
                        insertJourneyPs.executeUpdate();
                    }
                }
            }
            // Update the character data
            String updateCharacterSql = "UPDATE characters SET character_name = ?, hp = ?, money = ?, inventory = ? WHERE journey_id = ?";
            try (java.sql.PreparedStatement characterPs = database.getConnection().prepareStatement(updateCharacterSql)) {
                characterPs.setString(1, player.getCharacterName());
                characterPs.setInt(2, player.getHealth());
                characterPs.setInt(3, player.getMoney());
                // Convert the inventory to a CLOB and set it in the statement
                characterPs.setClob(4, DatabaseUtilities.inventoryToClob(player.getInventory().getInventory()));
                characterPs.setInt(5, journeyId);
                if (characterPs.executeUpdate() == 0) {
                    // If no rows were updated, insert a new character record
                    String insertCharacterSql = "INSERT INTO characters (journey_id, character_name, hp, money, inventory) VALUES (?, ?, ?, ?, ?)";
                    try (java.sql.PreparedStatement insertCharacterPs = database.getConnection().prepareStatement(insertCharacterSql)) {
                        insertCharacterPs.setInt(1, journeyId);
                        insertCharacterPs.setString(2, player.getCharacterName());
                        insertCharacterPs.setInt(3, player.getHealth());
                        insertCharacterPs.setInt(4, player.getMoney());
                        // Convert the inventory to a CLOB and set it in the prepared statement
                        insertCharacterPs.setClob(5, DatabaseUtilities.inventoryToClob(player.getInventory().getInventory()));
                        insertCharacterPs.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save journey: " + e.getMessage(), e);
        }
    }

}
