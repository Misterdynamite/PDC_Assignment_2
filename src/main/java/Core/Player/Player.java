package Core.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class Player extends Abstracts.Logic.Player {
    public static final int STARTING_HEALTH = 0;
    public static final int STARTING_MONEY = 0;
    public static final ArrayList<Inventory.Item> STARTING_INVENTORY
            = new ArrayList<>(Arrays.asList(Inventory.Item.BOMB));

    public Player() {
        super();
        this.inventory = new Inventory();
        this.setHealth(STARTING_HEALTH);
        this.setMoney(STARTING_MONEY);
        this.inventory.setInventory(STARTING_INVENTORY);

    }
}
