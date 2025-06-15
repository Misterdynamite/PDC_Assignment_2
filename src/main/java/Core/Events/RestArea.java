package Core.Events;

import Core.Player.Player;

import java.util.Random;

public class RestArea extends Abstracts.Logic.EncounterEvent {

    public RestArea(Core.Player.Player player) {
        super(player);
        this.OptionOne = new RestAreaSleep(player);
        this.setDescription("You find a rest area where you can either sleep or rest for a while.");
    }


    static class RestAreaSleep extends Abstracts.Logic.BridgingEvent {

        public RestAreaSleep(Player player) {
            super(player);
            this.conditionRequirement = "";
            this.optionDescription = "Rest to regain energy.";
            this.condition = true;
        }

        @Override
        public void OutCome() {
            Random random = new Random();
            int amount = random.nextInt(2) + 1;
            amount = Math.max(amount + player.getHealth(), Player.STARTING_HEALTH);
            player.setHealth(amount);
            setDescription("You rest for a while and feel refreshed. You regain " + amount + " hp up to " + this.player.getHealth() + ".");
        }
    }
}
