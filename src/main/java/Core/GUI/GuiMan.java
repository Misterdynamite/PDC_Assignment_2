/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.GUI;


import Abstracts.Logic.Player;
import com.aut603.Main.Main;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author DXG11
 */
public class GuiMan {
    private CardLayout cardL;
    private JPanel mainPanel;
    private JFrame mainFrame;
    private Main main;

    public GuiMan(Main main) {
        this.main = main;
        
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cardL = new CardLayout();
        mainPanel =  new JPanel(cardL);
        mainPanel.add(new MainMenu(this), "MAIN");
        mainPanel.add(new LoadGame(this), "LOAD");
        EventGui eventGui = new EventGui(this);
        mainPanel.add(eventGui, "EVENT");
        mainPanel.add(new OutCome(this), "OUTCOME");
        
        
        
        
    }
    
    public void nextEncounter(){
        
    }
    public void newPlayer(String name){
        main.newPlayer(name);
    }
    public void setPlayer(Player player){
        main.setPlayer(player);
    }
    
    
}
