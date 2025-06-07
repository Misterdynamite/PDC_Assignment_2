package Abstracts.Logic;

import java.util.ArrayList;

public abstract class Journey {
    protected final ArrayList<String> journeyLog = new ArrayList<>();
    protected Player player;
    protected int journeyId = 0;

    public Journey() {
    }

    public int getJourneyId() {
        return journeyId;
    }
    public void setJourneyId(int journeyId) {
        this.journeyId = journeyId;
    }

    public void addToJourney(String logEntry) {
        journeyLog.add(logEntry);
    }

    public ArrayList<String> getJourneyLog() {
        return journeyLog;
    }

    public void setJourneyLog(ArrayList<String> journeyLog) {
        this.journeyLog.clear();
        this.journeyLog.addAll(journeyLog);
    }


    public Player getPlayer() {
        return player;
    }


}