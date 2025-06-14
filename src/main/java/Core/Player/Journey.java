package Core.Player;

import Abstracts.Logic.Event;

public class Journey extends Abstracts.Logic.Journey {


    private Event currentEvent;

    public Journey(Player player) {
        super();
        this.player = player;
    }

    public Journey(String playerName) {
        super();
        this.player = new Player(playerName);
    }

    public Event getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(Event currentEvent) {
        this.currentEvent = currentEvent;
    }
}
