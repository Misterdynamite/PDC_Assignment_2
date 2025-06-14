package Core.Player;

public class Journey extends Abstracts.Logic.Journey {


    private String currentEvent;

    public Journey(Player player) {
        super();
        this.player = player;
    }

    public Journey(String playerName) {
        super();
        this.player = new Player(playerName);
    }

    public String getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(String currentEvent) {
        this.currentEvent = currentEvent;
    }
}
