package Core.Player;

import Abstracts.Logic.EncounterEvent;
import Abstracts.Logic.Event;

public class Journey extends Abstracts.Logic.Journey {


    private EncounterEvent currentEvent;

    public Journey(Player player) {
        super();
        this.player = player;
    }

    public Journey(String playerName) {
        super();
        this.player = new Player(playerName);
    }

    public EncounterEvent getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(EncounterEvent currentEvent) {
        this.currentEvent = currentEvent;
    }
}
