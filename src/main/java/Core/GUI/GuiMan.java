/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.GUI;


import Abstracts.Logic.BridgingEvent;
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
    private EventGui eventGui;
    private OutCome outComeGui;

    public GuiMan(Main main) {
        this.main = main;
        
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cardL = new CardLayout();
        mainPanel =  new JPanel(cardL);
        mainPanel.add(new MainMenu(this), "MAIN");
        mainPanel.add(new LoadGame(this), "LOAD");
        eventGui = new EventGui(this);
        mainPanel.add(eventGui, "EVENT");
        outComeGui = new OutCome(this);
        mainPanel.add(outComeGui, "OUTCOME");
        
        
        
        
    }
    
    public void nextEncounter(){
        eventGui.setNextEncounter(main.getNextEvent());
        cardL.show(eventGui, "EVENT");
    }
    public void newPlayer(String name){
        main.newPlayer(name);
        nextEncounter();
    }
    public void setPlayer(int ID){
        main.setPlayer(ID);
    }
    
    public void gotToOutCome(BridgingEvent outCome){
        outComeGui.setOutCome(outCome);
        cardL.show(mainPanel, "OUTCOME");
    }
    
    
}
