package Core.Events;

import Core.BridgingEvents.CliffClimbDown;
import Core.BridgingEvents.CliffRappelDown;

public class Cliff extends Abstracts.Logic.EncounterEvent {

    public Cliff(Core.Player.Player player) {
        super(player);
        this.OptionOne = new CliffRappelDown(player);
        this.OptionTwo = new CliffClimbDown(player);
    }

}
