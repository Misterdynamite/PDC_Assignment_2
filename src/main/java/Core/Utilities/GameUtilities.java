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

    public static EncounterEvent generateRandomEvent(Player player) {
        try {
            Class<?>[] eventClasses = Class.forName("Core.Events").getClasses();
            int idx = (int) (Math.random() * eventClasses.length);
            String eventName = eventClasses[idx].getSimpleName();
            return loadEvent(eventName, player);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate random event", e);
        }
    }
}
