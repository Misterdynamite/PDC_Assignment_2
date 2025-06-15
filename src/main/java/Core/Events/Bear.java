package Core.Events;

import Core.Player.Inventory;
import Core.Player.Player;

import java.util.Random;

public class Bear extends Abstracts.Logic.EncounterEvent {

    public Bear(Player player) {
        super(player);

        this.setDescription("You get attacked by a bear!");

        this.OptionOne = new UseSword(player);
        this.OptionTwo = new UseBow(player);
        this.OptionThree = new ActBig(player);
        this.OptionFour = new RunAway(player);
    }

    static class UseSword extends Abstracts.Logic.BridgingEvent {

        public UseSword(Player player) {
            super(player);
            this.conditionRequirement = "You must have a sword.";
            this.optionDescription = "Use your sword to fight the bear.";
            this.condition = player.getInventory().isItemOwned(Inventory.Item.SWORD);
        }

        @Override
        public void OutCome() {
            Random rand = new Random();
            int chance = rand.nextInt(2);

            if (chance == 1) {
                setDescription("You swing your sword and scare the bear away!");
            } else {
                setDescription("You try to hit the bear but miss. The bear scratches you as you flee.\nYou lost 1hp.");
                this.player.changeHealth(-1);

                if (rand.nextInt(2) == 1) {
                    setDescription(getDescription() + "\nYou drop your sword while escaping.");
                    this.player.getInventory().removeItem(Inventory.Item.SWORD);
                }

                setDescription(getDescription() + "\nYou now have " + this.player.getHealth() + "hp.");
            }
        }
    }

    static class UseBow extends Abstracts.Logic.BridgingEvent {

        public UseBow(Player player) {
            super(player);
            this.conditionRequirement = "You must have a bow.";
            this.optionDescription = "Use your bow to shoot at the bear.";
            this.condition = player.getInventory().isItemOwned(Inventory.Item.BOW);
        }

        @Override
        public void OutCome() {
            setDescription("You shoot an arrow at the bear, hitting it! The bear runs away.");
        }
    }

    static class ActBig extends Abstracts.Logic.BridgingEvent {

        public ActBig(Player player) {
            super(player);
            this.conditionRequirement = "";
            this.optionDescription = "Act big and try to scare the bear off.";
            this.condition = true;
        }

        @Override
        public void OutCome() {
            Random rand = new Random();
            int chance = rand.nextInt(4);

            if (chance == 3) {
                setDescription("You puff yourself up and shout. Amazingly, the bear runs off!");
            } else {
                setDescription("You try to scare the bear, but it isn't impressed.\nIt attacks you as you run.\nYou lost 1hp.");
                this.player.changeHealth(-1);
                setDescription(getDescription() + "\nYou now have " + this.player.getHealth() + "hp.");
            }
        }
    }

    static class RunAway extends Abstracts.Logic.BridgingEvent {

        public RunAway(Player player) {
            super(player);
            this.conditionRequirement = "";
            this.optionDescription = "Try to run away from the bear.";
            this.condition = true;
        }

        @Override
        public void OutCome() {
            Random rand = new Random();
            int chance = rand.nextInt(2);

            if (chance == 1) {
                setDescription("You manage to outrun the bear without injury.");
            } else {
                setDescription("You run from the bear but it manages to scratch you.\nYou lost 1hp.");
                this.player.changeHealth(-1);
                setDescription(getDescription() + "\nYou now have " + this.player.getHealth() + "hp.");
            }
        }
    }
}
