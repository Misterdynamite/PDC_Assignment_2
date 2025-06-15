package Tests;

import Core.Events.Shop;
import Core.Player.Inventory;
import Core.Player.Player;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    // Basic event test class.
    // Relatively difficult to test due to the nature of events being GUI loaded.
    // This class will test the basic functionality of the event system using the existing shop event.

    // If player doesn't have enough money, the option should not be available.
    @Test
    void testInsufficientMoney() {
        Player testPlayer = new Player("TestPlayer");
        testPlayer.setMoney(0);
        testPlayer.getInventory().setInventory(new ArrayList<Inventory.Item>());
        Shop shop = new Shop(testPlayer);
        assertFalse(shop.OptionOne.condition, "Should not be available when the player has no money.");
    }

    // If player has enough money, the option should be available.
    @Test
    void testSufficientMoney() {
        Player testPlayer = new Player("TestPlayer");
        testPlayer.setMoney(1000);
        testPlayer.getInventory().setInventory(new ArrayList<Inventory.Item>());

        Shop shop = new Shop(testPlayer);
        assertTrue(shop.OptionOne.condition, "Should be available as player has enough money.");
    }

    // If player inventory is full, the option should not be available.
    @Test
    void testInventoryFull() {
        Player testPlayer = new Player("TestPlayer");
        ArrayList<Inventory.Item> fullInventory = new ArrayList<>();
        for (int i = 0; i < Inventory.INVENTORY_CAPACITY; i++) {
            fullInventory.add(Inventory.Item.BOW);
        }
        testPlayer.getInventory().setInventory(fullInventory);
        testPlayer.setMoney(1000);

        Shop shop = new Shop(testPlayer);
        assertFalse(shop.OptionOne.condition, "Should be unavailable as player inventory is full.");
    }

    // If player already owns the item, the option should not be available.
    @Test
    void testPlayerOwnsItemAlready() {
        Player testPlayer = new Player("TestPlayer");
        ArrayList<Inventory.Item> fullInventory = new ArrayList<>();
        fullInventory.addAll(Arrays.asList(Inventory.Item.values()));

        testPlayer.getInventory().setInventory(fullInventory);
        testPlayer.setMoney(1000);

        Shop shop = new Shop(testPlayer);
        assertFalse(shop.OptionOne.condition, "Should be unavailable as player inventory contains every item.");
    }
}
