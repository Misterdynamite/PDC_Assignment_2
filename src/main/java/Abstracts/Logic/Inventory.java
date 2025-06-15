package Abstracts.Logic;

import java.util.ArrayList;

public abstract class Inventory {

    protected final ArrayList<Item> inventory = new ArrayList<Item>();
    public int INVENTORY_CAPACITY;

    public int getInventorySize() {
        return inventory.size();
    }

    public boolean removeItem(Core.Player.Inventory.Item item) {
        return this.inventory.remove(item);
    }

    public boolean addItem(Core.Player.Inventory.Item item) {
        return this.inventory.remove(item);
    }

    public boolean isItemOwned(Core.Player.Inventory.Item itemSearch) {
        return this.inventory.contains(itemSearch);
    }

    public static enum Item {}


}
