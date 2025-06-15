package Core.Player;

import java.util.ArrayList;

public class Inventory extends Abstracts.Logic.Inventory {
    // Primarily adapted from assignment 1

    // Inventory
    protected final ArrayList<Item> inventory = new ArrayList<>();
    public static int INVENTORY_CAPACITY = 3;
    // Item options
    public static enum Item {ROPE, BOMB, BOW, SWORD, LANTERN, LOCKPICK}

    // Remove item
    @Override
    public boolean removeItem(Item item) {
        return this.inventory.remove(item);
    }

    // Add item
    @Override
    public boolean addItem(Item item) {
        if (this.inventory.size() >= INVENTORY_CAPACITY) {
            return false;
        }
        return this.inventory.add(item);
    }

    // Check if item is owned
    @Override
    public boolean isItemOwned(Item itemSearch) {
        return this.inventory.contains(itemSearch);
    }
    // Get amount of items
    @Override
    public int getNumberOfItems() {
        return this.inventory.size();
    }
    // Get inventory
    public ArrayList<Item> getInventory() {
        return this.inventory;
    }
    // Set inventory
    public void setInventory(ArrayList<Inventory.Item> inventory) {
        this.inventory.clear();
        this.inventory.addAll(inventory);
    }
}
