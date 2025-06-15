package Core.Events;

import Core.Player.Inventory;
import Core.Player.Player;
import Core.Utilities.GameUtilities;
import Core.Utilities.StringUtilities;

import java.util.Random;

// Fully commented event code, rest are similar but simpler
public class Shop extends Abstracts.Logic.EncounterEvent {

    public Shop(Core.Player.Player player) {
        super(player);

        // Initialize the event options
        this.OptionOne = new ShopBuy(player);
        this.OptionTwo = new ShopBuy(player);
        this.OptionThree = new ShopBuy(player);
        this.OptionFour = new ExitShop(player);

        // Set the event description
        this.setDescription("You enter the shop. The shopkeeper greets you warmly and offers you a selection of items for sale.");
    }

    // If the player wishes to exit the shop, they can choose the ExitShop option
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

    // If the player wishes to buy an item, they can choose one of the ShopBuy options generated dynamically
    static class ShopBuy extends Abstracts.Logic.BridgingEvent {
        private final int cost;
        private final Inventory.Item item;

        // Constructor for the bridging event
        public ShopBuy(Player player) {
            super(player);
            Random random = new Random();
            item = GameUtilities.generateRandomItem();
            cost = random.nextInt(100) + 1;
            StringBuilder requirement = new StringBuilder("To buy a " + StringUtilities.toTitleCase(item.name()) + ", ");
            // Check the player's conditions for buying the item and provide feedback
            boolean first = true;
            if (this.player.getMoney() < cost) {
                requirement.append("you need ").append(cost).append(" gold");
                first = false;
            }
            if (this.player.getInventory().isItemOwned(item)) {
                if (!first) requirement.append(", ");
                requirement.append("you already own ").append(item.name());
                first = false;
            }
            if (player.getInventory().getNumberOfItems() >= Inventory.INVENTORY_CAPACITY) {
                if (!first) requirement.append(", ");
                requirement.append("your inventory must have space");
            }
            if (first) {
                requirement.append(".");
            }

            this.conditionRequirement = requirement.toString();
            // Set the brief option description
            this.optionDescription = "Buy " + StringUtilities.toTitleCase(item.name()) + " for " + cost + " gold.";
            // Actual boolean for whether the player can buy the item
            this.condition = (this.player.getMoney() >= cost &&
                    !this.player.getInventory().isItemOwned(item) &&
                    player.getInventory().getNumberOfItems() < Inventory.INVENTORY_CAPACITY);
        }

        @Override
        public void OutCome() {
            // If the player can buy the item, deduct the cost and add the item to their inventory
            setDescription("You buy " + StringUtilities.toTitleCase(item.name()) + " from the shop.");
            this.player.changeMoney(-this.cost);
            this.player.getInventory().addItem(this.item);
        }
    }

}
