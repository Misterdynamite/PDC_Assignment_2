package Core.BridgingEvents;

import Core.Player.Inventory;

public class CliffClimbDown extends Abstracts.Logic.BridgingEvent {

    public CliffClimbDown(Core.Player.Player player) {
        super(player);
        this.conditionRequirement = "";
        this.optionDescription = "Attempt to climb down.";
        this.condition = true;
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
