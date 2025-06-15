package Core.Events;

import Core.Player.Inventory;
import Core.Player.Player;
import Core.Utilities.GameUtilities;
import Core.Utilities.StringUtilities;

import java.util.Random;

public class Shop extends Abstracts.Logic.EncounterEvent {

    public Shop(Core.Player.Player player) {
        super(player);
        this.OptionOne = new ShopBuy(player);
        this.OptionTwo = new ShopBuy(player);
        this.OptionThree = new ShopBuy(player);
        this.OptionFour = new ExitShop(player);
        this.setDescription("You enter the shop. The shopkeeper greets you warmly and offers you a selection of items for sale.");
    }


    static class ExitShop extends Abstracts.Logic.BridgingEvent {

        public ExitShop(Player player) {
            super(player);
            this.conditionRequirement = "You can exit the shop.";
            this.optionDescription = "Exit the shop.";
            this.condition = true;
        }

        @Override
        public void OutCome() {
            setDescription("You exit the shop.");
        }
    }
    static class ShopBuy extends Abstracts.Logic.BridgingEvent {
        private int cost;
        private Inventory.Item item;

        public ShopBuy(Player player) {
            super(player);
            Random random = new Random();
            item = GameUtilities.generateRandomItem();
            cost = random.nextInt(100) + 1;
            StringBuilder requirement = new StringBuilder("To buy a " + StringUtilities.toTitleCase(item.name()) + ", ");

            boolean first = true;
            if (this.player.getMoney() < cost) {
                requirement.append("you need at least ").append(cost).append(" gold");
                first = false;
            }
            if (this.player.getInventory().isItemOwned(item)) {
                if (!first) requirement.append(", ");
                requirement.append("you must not already own ").append(item.name());
                first = false;
            }
            if (player.getInventory().getInventorySize() >= player.getInventory().INVENTORY_CAPACITY) {
                if (!first) requirement.append(", ");
                requirement.append("your inventory must have space");
            }
            if (first) {
                requirement.append("You can buy ").append(item.name()).append(" for ").append(cost).append(" gold.");
            }
            this.conditionRequirement = requirement.toString();
            this.optionDescription = "Buy " + StringUtilities.toTitleCase(item.name()) + " for " + cost + " gold.";
            this.condition = (this.player.getMoney() >= cost &&
                    !this.player.getInventory().isItemOwned(item) &&
                    player.getInventory().getInventorySize() < player.getInventory().INVENTORY_CAPACITY);
        }

        @Override
        public void OutCome() {
            setDescription("You buy " + StringUtilities.toTitleCase(item.name()) + " from the shop.");
            this.player.changeMoney(-this.cost);
            this.player.getInventory().addItem(this.item);
        }
    }

}
