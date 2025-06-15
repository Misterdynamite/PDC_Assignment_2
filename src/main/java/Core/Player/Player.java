package Core.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class Player extends Abstracts.Logic.Player {
    public static final int STARTING_HEALTH = 3;
    public static final int STARTING_MONEY = 0;
    public static final ArrayList<Inventory.Item> STARTING_INVENTORY
            = new ArrayList<>(Arrays.asList(Inventory.Item.BOMB));
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

    public String toString() {
        return "Player{" +
                "characterName='" + characterName + '\'' +
                ", health=" + health +
                ", money=" + money +
                ", inventory=" + inventory +
                '}';
    }
}
