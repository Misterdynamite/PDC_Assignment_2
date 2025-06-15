package Core.BridgingEvents;

import Core.Player.Inventory;

import java.util.Random;

public class CliffClimbDown extends Abstracts.Logic.BridgingEvent {

    public CliffClimbDown(Core.Player.Player player) {
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
            setDescription("You attempt to climb down the cliff, but you slip and fall and take 1 damage.");
            this.player.changeHealth(1);
        }
    }
}
