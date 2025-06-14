/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aut603.Main;

import Abstracts.Database.Database;
import Abstracts.Logic.EncounterEvent;
import Core.Player.Journey;

/**
 *
 * @author DXG11
 */
public class Main {
    public Journey journey;
    
    public EncounterEvent getNextEvent(){
        return null;
    }
    
    public void newPlayer(String name){
        this.journey = new Journey(name);
    }
    
    public void setPlayer(int ID){
        this.journey = 
    }
    
    
    
}
