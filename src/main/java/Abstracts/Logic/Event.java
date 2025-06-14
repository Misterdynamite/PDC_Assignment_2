/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Abstracts.Logic;

/**
 * @author DXG11
 */


public abstract class Event {

      protected String description;
      Player player;
     public void setPlayer(Player player){
         this.player = player;
     }
     public Player getPlayer(){
         return this.player;
     }
     public void setDescription(String description){
         this.description = description;
     }
     
     public String getDescription(){
         return this.description;
     }
     


}
