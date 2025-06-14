package Core.Utilities;

import Abstracts.Logic.Event;

public class GameUtilities {

    public static Event loadEvent(String eventName) {
        try {
            Class<?> eventClass = Class.forName("Core.Events." + eventName);
            return (Event) eventClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load event: " + eventName, e);
        }
    }
}
