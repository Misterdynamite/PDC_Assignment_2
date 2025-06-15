package Core.Events;

import Core.Player.Inventory;
import Core.Player.Player;

import java.util.Random;

public class Cliff extends Abstracts.Logic.EncounterEvent {

    public Cliff(Core.Player.Player player) {
        super(player);
        this.OptionOne = new CliffRappelDown(player);
        this.OptionTwo = new CliffClimbDown(player);
        this.setDescription("You come across a steep cliff. You can either climb down or rappel down using your rope.");
    }

    static class CliffClimbDown extends Abstracts.Logic.BridgingEvent {

        public CliffClimbDown(Player player) {
            super(player);
            this.conditionRequirement = "";
            this.optionDescription = "Attempt to climb down.";
            this.condition = true;
        }

        @Override
        public void OutCome() {
            Random random = new Random();

            if (random.nextInt(100) < 50) {
                setDescription("You successfully climb down the cliff.");
            } else {
                setDescription("You attempt to climb down the cliff, but you slip and fall and take 1 damage, you now have " + (this.player.getHealth() - 1) + " health.");
                this.player.changeHealth(-1);
            }
        }
    }
    static class CliffRappelDown extends Abstracts.Logic.BridgingEvent {

        public CliffRappelDown(Player player) {
            super(player);
            this.conditionRequirement = "You must have rope.";
            this.optionDescription = "Climb down carefully using your rope.";
            this.condition = (this.player.getInventory().isItemOwned(Inventory.Item.ROPE));
        }

        @Override
        public void OutCome() {
            Random random = new Random();
            if (random.nextInt(100) < 75) {
                setDescription("You successfully rappel down the cliff using your rope.");
            } else {
                setDescription("You successfully rappel down the cliff using your rope, but the rope breaks.");
                this.player.getInventory().removeItem(Inventory.Item.ROPE);
            }
        }
    }
}
