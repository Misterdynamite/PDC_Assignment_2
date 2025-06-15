/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aut603.Main;

import Core.Database.Database;
import Abstracts.Logic.EncounterEvent;
import Core.GUI.GuiMan;
import Core.Player.Journey;
import Core.Player.Player;
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
    
    public void initialiseDB() throws SQLException{
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
        journey.addToJourney(e.getClass().getSimpleName());
        journey.setCurrentEvent(e);
    }
    public void save(){
        db.saveJourney(journey);
    }
    public Player getPlayer() {
        return journey.getPlayer();
    }
    
    public int getHP(){
        return journey.getPlayer().getHealth();
    }
    
    public static void main(String[] args) throws SQLException {
        Main main = new Main();
        main.initialiseDB();
        GuiMan gui = new GuiMan(main);
        gui.gotToMainMenu();
    }
    
}
