package Core.Player;

import java.util.ArrayList;

public class Inventory extends Abstracts.Logic.Inventory {

    protected final ArrayList<Item> inventory = new ArrayList<>();

    public static enum Item {ROPE, BOMB, BOW, SWORD, LANTERN, LOCKPICK}

    @Override
    public boolean removeItem(Item item) {
        return this.inventory.remove(item);
    }

    @Override
    public boolean addItem(Item item) {
        if (this.inventory.size() >= INVENTORY_CAPACITY) {
            return false;
        }
        return this.inventory.add(item);
    }

    @Override
    public boolean isItemOwned(Item itemSearch) {
        return this.inventory.contains(itemSearch);
    }

    @Override
    public int getInventorySize() {
        return this.inventory.size();
    }

    public ArrayList<Item> getInventory() {
        return this.inventory;
    }

    public void setInventory(ArrayList<Inventory.Item> inventory) {
        this.inventory.clear();
        this.inventory.addAll(inventory);
    }
}
