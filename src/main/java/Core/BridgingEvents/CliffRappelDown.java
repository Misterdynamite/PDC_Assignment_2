package Core.BridgingEvents;

import Core.Player.Inventory;

public class CliffRappelDown extends Abstracts.Logic.BridgingEvent {

    public CliffRappelDown(Core.Player.Player player) {
        super(player);
        this.conditionRequirement = "You must have rope.";
        this.optionDescription = "Climb down carefully using your rope.";
        this.condition = (this.player.getInventory().isItemOwned(Inventory.Item.ROPE));
    }

    @Override
    public void OutCome() {
        if (condition) {
            return;
        } else {
            return;
        }
    }
}
