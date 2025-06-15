package Core.Player;

import Abstracts.Logic.EncounterEvent;
import Abstracts.Logic.Event;

public class Journey extends Abstracts.Logic.Journey {

    protected Player player;

    private EncounterEvent currentEvent;

    // Constructor for loading an existing player journey
    public Journey(Player player) {
        super();
        this.player = player;
    }

    // Constructor for creating a new player journey
    public Journey(String playerName) {
        super();
        this.player = new Player(playerName);
    }


    // Get player
    @Override
    public Player getPlayer() {
        return player;
    }

    // Get current event
    public EncounterEvent getCurrentEvent() {
        return currentEvent;
    }

    // Set current event
    public void setCurrentEvent(EncounterEvent currentEvent) {
        this.currentEvent = currentEvent;
    }
}
