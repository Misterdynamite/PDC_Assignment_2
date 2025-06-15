package Tests;

import Core.Player.Inventory;
import Core.Player.Inventory.Item;
import Core.Player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    // Concrete subclass of Player for testing
    static class TestPlayer extends Player {
        public TestPlayer(Inventory inventory) {
            super("TestPlayer");
            this.inventory = inventory;
        }
    }

    private TestPlayer player;
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        inventory.INVENTORY_CAPACITY = 5; // directly set protected capacity
        player = new TestPlayer(inventory);
    }

    @Test
    void testSetAndGetCharacterName() {
        player.setCharacterName("Link");
        assertEquals("Link", player.getCharacterName());
    }

    @Test
    void testSetAndGetMoney() {
        player.setMoney(100);
        assertEquals(100, player.getMoney());
    }

    @Test
    void testChangeMoney() {
        player.setMoney(50);
        player.changeMoney(30);
        assertEquals(80, player.getMoney());

        player.changeMoney(-20);
        assertEquals(60, player.getMoney());
    }

    @Test
    void testSetAndGetHealth() {
        player.setHealth(75);
        assertEquals(75, player.getHealth());
    }

    @Test
    void testChangeHealth() {
        player.setHealth(40);
        player.changeHealth(10);
        assertEquals(50, player.getHealth());

        player.changeHealth(-15);
        assertEquals(35, player.getHealth());
    }

    @Test
    void testIsItemOwned() {
        assertFalse(player.getInventory().isItemOwned(Item.SWORD));

        inventory.addItem(Item.SWORD);
        System.out.println("Inventory after adding SWORD: " + inventory.getInventory());
        assertTrue(player.getInventory().isItemOwned(Item.SWORD));
    }

    @Test
    void testAddItemWithinCapacity() {
        assertTrue(inventory.addItem(Item.ROPE));
        assertTrue(inventory.addItem(Item.BOMB));
        assertEquals(2, inventory.getInventorySize());
    }

    @Test
    void testAddItemExceedingCapacity() {
        inventory.INVENTORY_CAPACITY = 2;
        inventory.addItem(Item.LANTERN);
        inventory.addItem(Item.BOW);

        assertFalse(inventory.addItem(Item.SWORD));
        assertEquals(2, inventory.getInventorySize());
    }

    @Test
    void testRemoveItem() {
        inventory.addItem(Item.LOCKPICK);
        assertTrue(player.getInventory().isItemOwned(Item.LOCKPICK));

        inventory.removeItem(Item.LOCKPICK);
        assertFalse(player.getInventory().isItemOwned(Item.LOCKPICK));
    }

    @Test
    void testSetInventory() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(Item.BOW);
        items.add(Item.SWORD);

        inventory.setInventory(items);
        assertEquals(2, inventory.getInventorySize());
        assertTrue(player.getInventory().isItemOwned(Item.BOW));
        assertTrue(player.getInventory().isItemOwned(Item.SWORD));
    }

    @Test
    void testGetInventory() {
        assertEquals(inventory, player.getInventory());
    }
}