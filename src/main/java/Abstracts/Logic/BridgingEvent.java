/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Abstracts.Logic;

/**
 * @author DXG11
 */

import Core.Player.*;

public abstract class BridgingEvent extends Event{
    
    public BridgingEvent(Player player){
        super(player);
        
    }

    public String conditionRequirement;
    public String optionDescription;
    public boolean condition;
    public abstract void OutCome ();

}
