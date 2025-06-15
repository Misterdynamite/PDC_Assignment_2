package Tests;

// test does clear out all the tables in the database for testing purposes due to using the same class

import Abstracts.Logic.Event;
import Core.Database.Database;
import Core.Database.DatabaseCreation;
import Core.Database.DatabaseDestruction;
import Core.Events.TestEvent;
import Core.Player.Journey;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    Database database;
    DatabaseDestruction destruction;
    DatabaseCreation creation;

    @BeforeEach
    void setUp() throws SQLException {

        database = Database.getInstance();
        destruction = new DatabaseDestruction(database);
        creation = new DatabaseCreation(database);
        destruction.truncateTables();

    }

    public void createDummyJourney() {

        Random random = new Random();
        Journey journey = new Journey("TestUser");
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 12; j++) {
            sb.append((char) ('A' + random.nextInt(26)));
        }
        journey.addToJourney(sb.toString());
        journey.setCurrentEvent(new TestEvent());
        database.saveJourney(journey);


    }

    @AfterEach
    void tearDown() {
        destruction.truncateTables();
    }

    @Test
    void tableCreationTest() throws SQLException {
        destruction.dropTables();
        creation.initaliseDatabase();
        Connection conn = database.getConnection();
        ResultSet rs = conn.getMetaData().getTables(null, null, "%", new String[]{"TABLE"});
        boolean journeyExists = false;
        boolean charactersExists = false;
        while (rs.next()) {
            String tableName = rs.getString("TABLE_NAME");
            if ("journeys".equalsIgnoreCase(tableName)) journeyExists = true;
            if ("characters".equalsIgnoreCase(tableName)) charactersExists = true;
        }
        assertTrue(journeyExists, "Table 'Journey' should exist");
        assertTrue(charactersExists, "Table 'Characters' should exist");
    }

    @Test
    void saveAndLoadJourneyTest() {
        Journey journey = new Journey("TestUser");
        journey.setCurrentEvent(new TestEvent());
        database.saveJourney(journey);
        Journey loadedJourney = database.loadJourneyFromIndex(1);
        assertNotNull(loadedJourney, "Loaded journey should not be null");
        assertEquals(journey.getJourneyId(), loadedJourney.getJourneyId(),
                "Loaded journey ID should match saved journey ID");
        assertEquals(journey.getPlayer().getCharacterName(), loadedJourney.getPlayer().getCharacterName(),
                "Loaded character name should match saved character name");
        assertEquals(journey.getPlayer().getHealth(), loadedJourney.getPlayer().getHealth(),
                "Loaded health should match saved health");
        assertEquals(journey.getPlayer().getMoney(), loadedJourney.getPlayer().getMoney(),
                "Loaded money should match saved money");
        assertEquals(journey.getJourneyLog().toString(), loadedJourney.getJourneyLog().toString(),
                "Loaded journey log should match saved journey log");
        assertEquals(journey.getCurrentEvent().getClass().getSimpleName(), loadedJourney.getCurrentEvent().getClass().getSimpleName(),
                "Loaded current event should match saved current event");
        assertEquals(journey.getPlayer().getInventory().getInventory(), loadedJourney.getPlayer().getInventory().getInventory(),
                "Loaded inventory should match saved inventory");

    }

    @Test
    void iterativeJourneySaveTest() {
        Journey journey = new Journey("TestUser");
        journey.setCurrentEvent(new TestEvent());
        for (int i = 1; i < 30; i++) {
            journey.getPlayer().changeMoney(i);
            database.saveJourney(journey);
            Journey loadedJourney = database.loadJourneyFromIndex(1);

            assertEquals(journey.getPlayer().getMoney(), loadedJourney.getPlayer().getMoney(),
                    "Money should be incremented correctly after each save");
        }


    }

    @Test
    void viewJourneyTest() {

        for (int i = 0; i < 30; i++) {
            createDummyJourney();
        }

        ArrayList<String[][]> aliveJourneys = database.getAliveJourneysBrief();
        int count = 1;
        for (String[][] journey : aliveJourneys) {
            assertEquals("TestUser", journey[1][0], "Character name should be 'TestUser'");
            assertEquals(count, Integer.parseInt(journey[0][0]), "Journey ID should match the count");
            count++;
        }
    }


}