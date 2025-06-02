package Abstracts.Logic;

import java.util.ArrayList;

public abstract class Inventory {

    protected final ArrayList<Core.Player.Inventory.Item> inventory = new ArrayList<Core.Player.Inventory.Item>();
    protected int INVENTORY_CAPACITY;

    public boolean isItemOwned(Core.Player.Inventory.Item itemSearch) {
        for (Core.Player.Inventory.Item item : this.inventory) {
            if (item.equals(itemSearch)) {
                return true;
            }
        }
        return false;
    }

    public boolean addItem(Core.Player.Inventory.Item item) {
        if (this.getInventorySize() < this.INVENTORY_CAPACITY) {
            this.inventory.add(item);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeItem(Item itemRemove) {
        return this.inventory.remove(itemRemove);
    }

    public int getInventorySize() {
        return inventory.size();
    }

    public ArrayList<Core.Player.Inventory.Item> getInventory() {
        return this.inventory;
    }
    public void setInventory(ArrayList<Core.Player.Inventory.Item> STARTING_INVENTORY) {
    }

    public static enum Item {}


}
