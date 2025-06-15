package Core.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class Player extends Abstracts.Logic.Player {
    // Primarily adapted from assignment 1
    public static final int STARTING_HEALTH = 5;
    public static final int STARTING_MONEY = 50;
    public static final ArrayList<Inventory.Item> STARTING_INVENTORY
            = new ArrayList<>(Arrays.asList(Inventory.Item.SWORD, Inventory.Item.ROPE));
    protected Inventory inventory;

    public Player(String name) {
        super();
        this.inventory = new Inventory();
        this.characterName = name;
        this.setHealth(STARTING_HEALTH);
        this.setMoney(STARTING_MONEY);
        this.inventory.setInventory(STARTING_INVENTORY);

    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    // Debug
    public String toString() {
        return "Player{" +
                "characterName='" + characterName + '\'' +
                ", health=" + health +
                ", money=" + money +
                ", inventory=" + inventory +
                '}';
    }
}
