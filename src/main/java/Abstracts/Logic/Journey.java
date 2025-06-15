package Abstracts.Logic;

import java.util.ArrayList;

public abstract class Journey {
    protected final ArrayList<String> journeyLog = new ArrayList<>();
    protected Player player;
    protected int journeyId = 0;

    public Journey() {
    }

    // Get journey ID
    public int getJourneyId() {
        return journeyId;
    }
    // Set journey ID
    public void setJourneyId(int journeyId) {
        this.journeyId = journeyId;
    }

    // Add a log entry to the journey log
    public void addToJourney(String logEntry) {
        journeyLog.add(logEntry);
    }

    // Get the journey log
    public ArrayList<String> getJourneyLog() {
        return journeyLog;
    }

    // Set the journey log
    public void setJourneyLog(ArrayList<String> journeyLog) {
        this.journeyLog.clear();
        this.journeyLog.addAll(journeyLog);
    }

    // Get the player associated with this journey
    public Player getPlayer() {
        return player;
    }


}