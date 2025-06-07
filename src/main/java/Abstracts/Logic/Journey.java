package Abstracts.Logic;

import java.util.ArrayList;

public abstract class Journey {
    protected final ArrayList<String> journeyLog = new ArrayList<>();
    protected Player player;

    public Journey() {
    }

    public void addToJourney(String logEntry) {
        journeyLog.add(logEntry);
    }

    public String getJourneyLog() {
        StringBuilder log = new StringBuilder();
        for (String entry : journeyLog) {
            log.append(entry).append("\n");
        }
        return log.toString();
    }

    public Player getPlayer() {
        return player;
    }
}