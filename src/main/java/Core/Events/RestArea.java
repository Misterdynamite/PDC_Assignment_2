package Core.Events;

import Core.Player.Inventory;
import Core.Player.Player;
import Core.Utilities.GameUtilities;
import Core.Utilities.StringUtilities;

import java.util.Random;

public class RestArea extends Abstracts.Logic.EncounterEvent {

    public RestArea(Core.Player.Player player) {
        super(player);
        this.OptionOne = new RestAreaSleep(player);
        this.OptionTwo = new RestAreaLookAround(player);
        this.setDescription("You find a area where you can rest for a while.");
    }

    static class RestAreaLookAround extends Abstracts.Logic.BridgingEvent {

        public RestAreaLookAround(Player player) {
            super(player);
            this.conditionRequirement = "";
            this.optionDescription = "Look around the area before resting.";
            this.condition = true;
        }

        @Override
        public void OutCome() {
            Random random = new Random();
            int choice = random.nextInt(3) + 1;
            if (choice == 1) {
                Inventory.Item item = GameUtilities.generateRandomItem();

                if (player.getInventory().addItem(item)) {
                    setDescription("You find a " + StringUtilities.toTitleCase(item.name()) + " lying around and pick it up.");
                } else {
                    setDescription("You find a " + StringUtilities.toTitleCase(item.name()) + ", but your inventory is full. You can't pick it up.");
                }
            } else if (choice == 2) {
                int amountIncrement = random.nextInt(100) + 1;
                player.changeMoney(amountIncrement);
                setDescription("You find " + amountIncrement + " gold coins lying around and pick them up. You now have " + player.getMoney() + " gold coins.");

            } else {
                setDescription("You notice some strange markings on the ground, but nothing of interest.");
            }

        }
    }

    static class RestAreaSleep extends Abstracts.Logic.BridgingEvent {

        public RestAreaSleep(Player player) {
            super(player);
            this.conditionRequirement = "";
            this.optionDescription = "Rest early to regain energy.";
            this.condition = true;
        }

        @Override
        public void OutCome() {
            Random random = new Random();
            int amountIncrement = random.nextInt(2) + 1;
            int amount = Math.max(amountIncrement + player.getHealth(), Player.STARTING_HEALTH);
            player.setHealth(amount);
            setDescription("You rest for a while and feel refreshed. You regain " + amountIncrement + "hp up to " + this.player.getHealth() + "hp.");
        }
    }


}
