package Core.Player;

import java.util.ArrayList;

public class Inventory extends Abstracts.Logic.Inventory {
    public static enum Item {ROPE, BOMB, BOW, SWORD, LANTERN, LOCKPICK}

    public void setInventory(ArrayList<Inventory.Item> inventory) {
        this.inventory.clear();
        this.inventory.addAll(inventory);
    }
}
