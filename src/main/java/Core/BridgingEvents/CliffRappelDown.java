package Core.BridgingEvents;

import Core.Player.Inventory;

import java.util.Random;

public class CliffRappelDown extends Abstracts.Logic.BridgingEvent {

    public CliffRappelDown(Core.Player.Player player) {
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
