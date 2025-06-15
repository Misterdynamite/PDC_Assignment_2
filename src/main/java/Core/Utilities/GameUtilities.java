package Core.Utilities;

import Abstracts.Logic.EncounterEvent;
import Abstracts.Logic.Event;
import Core.Player.Player;

public class GameUtilities {

    public static EncounterEvent loadEvent(String eventName, Player player) {
        try {
            Class<?> eventClass = Class.forName("Core.Events." + eventName);
            return (EncounterEvent) eventClass.getDeclaredConstructor(Player.class).newInstance(player);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load event: " + eventName, e);
        }
    }
}
