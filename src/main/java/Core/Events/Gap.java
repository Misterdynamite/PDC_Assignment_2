package Core.Events;

import Core.Player.Inventory;
import Core.Player.Player;

public class Gap extends Abstracts.Logic.EncounterEvent {
    // Adapted from assignment 1
    public Gap(Player player) {
        super(player);

        this.setDescription(player.getCharacterName() + " comes across a ravine that splits the earth in two.");

        this.OptionOne = new UseRope(player);
        this.OptionTwo = new RocketJump(player);
        this.OptionThree = new WanderAway(player);
    }

    static class UseRope extends Abstracts.Logic.BridgingEvent {

        public UseRope(Player player) {
            super(player);
            this.optionDescription = "Use your rope to make a bridge and cross the gap.";
            this.conditionRequirement = "You have no rope.";
            this.condition = player.getInventory().isItemOwned(Inventory.Item.ROPE);
        }

        @Override
        public void OutCome() {
            this.player.getInventory().removeItem(Inventory.Item.ROPE);
            this.player.getInventory().addItem(Inventory.Item.BOW);

            setDescription("You tie the rope in a lasso hoop before whipping it around above your head and letting it loose.\n" +
                    "It snags on a convenient rock, you find another, slightly less convenient tree stump on your side of the gap and tie the rope to it.\n" +
                    "You cross safely and find a bow propped up against the rock on the other side.");
        }
    }

    static class RocketJump extends Abstracts.Logic.BridgingEvent {

        public RocketJump(Player player) {
            super(player);
            this.optionDescription = "Rocket jump.";
            this.conditionRequirement = "You lack the madness and explosives required to cross.";
            this.condition = player.getInventory().isItemOwned(Inventory.Item.BOMB);
        }

        @Override
        public void OutCome() {
            this.player.getInventory().removeItem(Inventory.Item.BOMB);
            this.player.changeHealth(-1);
            this.player.getInventory().addItem(Inventory.Item.BOMB);

            setDescription("You throw the bomb just behind you as you take a running leap over the precipice.\n" +
                    "For a moment you fear that reality has caught up with you before a feeling of searing pain and a deafening roar propel you forward and up.\n" +
                    "You land in a slightly singed heap before stumbling onward.\nYou lost 1hp. You have " + player.getHealth() + " left.\n" +
                    "As you begin to leave, you stumble across something on the ground. It's another bomb!");
        }
    }

    static class WanderAway extends Abstracts.Logic.BridgingEvent {

        public WanderAway(Player player) {
            super(player);
            this.optionDescription = "Wander until you find something else.";
            this.conditionRequirement = "";
            this.condition = true;
        }

        @Override
        public void OutCome() {
            setDescription(player.getCharacterName() + " decides to wander off in search of a different path.");
        }
    }
}
