package Core.Utilities;

import Abstracts.Logic.EncounterEvent;
import Abstracts.Logic.Event;
import Core.Player.Inventory;
import Core.Player.Player;
import org.reflections.Reflections;

import java.util.Set;

public class GameUtilities {
    // Loads an event by its name from the Core.Events package
    public static EncounterEvent loadEvent(String eventName, Player player) {
        try {
            Class<?> eventClass = Class.forName("Core.Events." + eventName);
            return (EncounterEvent) eventClass.getDeclaredConstructor(Player.class).newInstance(player);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load event: " + eventName, e);
        }
    }
    // Selects a random event from the available events in the Core.Events package
    // Mildly adapted from assignment 1, but mostly different as it uses the Reflections library instead of a hardcoded list
    // Chatgpt consulted for the Reflections library usage
    public static EncounterEvent generateRandomEvent(Player player) {
        try {
            Reflections reflections = new Reflections("Core.Events");
            Set<Class<? extends EncounterEvent>> eventClassSet = reflections.getSubTypesOf(EncounterEvent.class);
            Class<?>[] eventClasses = eventClassSet.toArray(new Class<?>[0]);
            int idx = (int) (Math.random() * eventClasses.length);
            String eventName = eventClasses[idx].getSimpleName();
            System.out.println("Generating random event: " + eventName);
            return loadEvent(eventName, player);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate random event", e);
        }
    }

    public static Inventory.Item generateRandomItem() {
        return Inventory.Item.values()[(int) (Math.random() * Inventory.Item.values().length)];
    }
}
