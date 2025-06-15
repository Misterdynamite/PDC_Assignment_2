/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aut603.Main;

import Core.Database.Database;
import Abstracts.Logic.EncounterEvent;
import Abstracts.Logic.Event;
import Core.Player.Journey;
import java.sql.SQLException;
import java.util.List;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author DXG11
 */
public class Main {
    
    private Database db;
    private Journey journey;
    private ArrayList<String[][]> saves;
    

    public Main() throws SQLException {
    }

    public EncounterEvent getNextEvent(){
        return null;
    }
    
    public void initaliseDB() throws SQLException{
         db = Database.getInstance();
    }
    
    public void newPlayer(String name){
        this.journey = new Journey(name);
    }
    
    public void setPlayer(int idx){
        this.journey = db.loadJourneyFromIndex(idx);
    }
    public void loadSaves(){
        saves = db.getAliveJourneysBrief();
    }
    
    public ArrayList<String[][]> getSaves(){
        return saves;
    }
    
    public EncounterEvent getSavedEncounter(){
        return journey.getCurrentEvent();
    }
    public void setCurrentEncounter(EncounterEvent e){
        journey.setCurrentEvent(e);
    }
    public void save(){
        db.saveJourney(journey);
    }
    
}
