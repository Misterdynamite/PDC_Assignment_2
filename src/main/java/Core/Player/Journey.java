package Core.Player;

public class Journey extends Abstracts.Logic.Journey {



    public Journey (Player player) {
        super();
        this.player = player;
    }
    public Journey (String playerName) {
        super();
        this.player = new Player(playerName);
    }

}
