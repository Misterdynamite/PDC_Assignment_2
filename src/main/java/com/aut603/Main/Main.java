/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aut603.Main;

import Core.Database.Database;
import Abstracts.Logic.EncounterEvent;
import Core.Player.Journey;

import java.sql.SQLException;

/**
 *
 * @author DXG11
 */
public class Main {
    public Database db = Core.Database.Database.getInstance();
    public Journey journey;

    public Main() throws SQLException {
    }

    public EncounterEvent getNextEvent(){
        return null;
    }
    
    public void newPlayer(String name){
        this.journey = new Journey(name);
    }
    
    public void setPlayer(int idx){
        this.journey = db.loadJourneyFromIndex(idx);
    }
    
    
    
}
