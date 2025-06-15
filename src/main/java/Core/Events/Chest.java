package Core.Events;

import Core.Player.Inventory;
import Core.Player.Player;
import Core.Utilities.GameUtilities;
import Core.Utilities.StringUtilities;

import java.util.Random;

public class Chest extends Abstracts.Logic.EncounterEvent {

    public Chest(Player player) {
        super(player);

        this.setDescription(player.getCharacterName() + " finds a chest.");

        boolean requiresLockpick = new Random().nextBoolean();

        if (requiresLockpick) {
            this.OptionOne = new OpenWithLockpick(player);
        } else {
            this.OptionOne = new OpenNormally(player);
        }

        this.OptionTwo = new LeaveChest(player);
    }

    static class OpenWithLockpick extends Abstracts.Logic.BridgingEvent {

        public OpenWithLockpick(Player player) {
            super(player);
            this.optionDescription = "Use your lockpick to open the chest.";
            this.conditionRequirement = "You must have a lockpick.";
            this.condition = player.getInventory().isItemOwned(Inventory.Item.LOCKPICK);
        }

        @Override
        public void OutCome() {
            Inventory.Item itemFound = GameUtilities.generateRandomItem();

            StringBuilder output = new StringBuilder();
            output.append(player.getCharacterName()).append(" used a lockpick to open the chest and found ");

            if (player.getInventory().addItem(itemFound)) {
                output.append("a ").append(StringUtilities.toTitleCase(itemFound.name())).append(".");
            } else {
                int coins = new Random().nextInt(100) + 1;
                output.append(coins).append(" coins instead.");
            }

            setDescription(output.toString());
        }
    }

    static class OpenNormally extends Abstracts.Logic.BridgingEvent {

        public OpenNormally(Player player) {
            super(player);
            this.optionDescription = "Open the chest.";
            this.conditionRequirement = "";
            this.condition = true;
        }

        @Override
        public void OutCome() {
            Inventory.Item itemFound = GameUtilities.generateRandomItem();

            StringBuilder output = new StringBuilder();
            output.append(player.getCharacterName()).append(" opened the chest and found ");

            if (player.getInventory().addItem(itemFound)) {
                output.append("a ").append(StringUtilities.toTitleCase(itemFound.name())).append(".");
            } else {
                int coins = new Random().nextInt(100) + 1;
                output.append(coins).append(" coins instead.");
            }

            setDescription(output.toString());
        }
    }

    static class LeaveChest extends Abstracts.Logic.BridgingEvent {

        public LeaveChest(Player player) {
            super(player);
            this.optionDescription = "Leave the chest.";
            this.conditionRequirement = "";
            this.condition = true;
        }

        @Override
        public void OutCome() {
            setDescription(player.getCharacterName() + " decides to leave the chest untouched.");
        }
    }
}

