package Tests;

import Core.Events.Shop;
import Core.Player.Inventory;
import Core.Player.Player;

import org.junit.jupiter.api.*;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testInsufficientGold() {
        Player testPlayer = new Player("TestPlayer");
        testPlayer.setMoney(0);
        testPlayer.getInventory().setInventory(new ArrayList<Inventory.Item>());
        Shop shop = new Shop(testPlayer);
        assertFalse(shop.OptionOne.condition, "Should not be available when the player has no money.");
    }

    @Test
    void testSufficientFunds() {
        Player testPlayer = new Player("TestPlayer");
        testPlayer.setMoney(1000);
        testPlayer.getInventory().setInventory(new ArrayList<Inventory.Item>());

        Shop shop = new Shop(testPlayer);
        assertTrue(shop.OptionOne.condition, "Should be available as player has enough money.");
    }
}
