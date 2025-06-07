package Core.Player;

public class Journey extends Abstracts.Logic.Journey {

    public Journey() {
        super();
        this.player = null;
    }
    public Journey (Player player) {
        super();
        this.player = player;
    }
    public Journey (String playerName) {
        super();
        this.player = new Player();
        this.player.setCharacterName(playerName);
    }

}
