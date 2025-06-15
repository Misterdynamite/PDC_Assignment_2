package Core.Utilities;

import Abstracts.Logic.EncounterEvent;
import Abstracts.Logic.Event;

public class GameUtilities {

    public static EncounterEvent loadEvent(String eventName) {
        try {
            Class<?> eventClass = Class.forName("Core.Events." + eventName);
            return (EncounterEvent) eventClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load event: " + eventName, e);
        }
    }
}
