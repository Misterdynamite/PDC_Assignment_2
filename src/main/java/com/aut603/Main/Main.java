/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aut603.Main;

import Abstracts.Logic.EncounterEvent;

/**
 *
 * @author DXG11
 */
public class Main {
    public Player player;
    
    public EncounterEvent getNextEvent(){
        return null;
    }
    
    public void newPlayer(String name){
        this.player = new Player(name);
    }
    
    public void setPlayer(Player player){
        this.player = player;
    }
    
    
    
}
