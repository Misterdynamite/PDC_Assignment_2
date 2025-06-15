/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.GUI;


import Abstracts.Logic.BridgingEvent;
import Abstracts.Logic.EncounterEvent;
import Core.Player.Player;
import Core.Utilities.GameUtilities;
import com.aut603.Main.Main;
import java.awt.CardLayout;
import java.util.ArrayList;
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
    private LoadGame loadGameGui;
    private MainMenu mainMenuGui;

    public GuiMan(Main main) {
        this.main = main;
        
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cardL = new CardLayout();
        mainPanel =  new JPanel(cardL);
        mainMenuGui = new MainMenu(this);
        mainPanel.add(mainMenuGui, "MAIN");
        loadGameGui = new LoadGame(this);
        mainPanel.add(loadGameGui, "LOAD");
        eventGui = new EventGui(this);
        mainPanel.add(eventGui, "EVENT");
        outComeGui = new OutCome(this);
        mainPanel.add(outComeGui, "OUTCOME");
    }
    
    public void nextEncounter(){
        Player player = (Player) main.getSavedEncounter().getPlayer();
        eventGui.setNextEncounter(GameUtilities.generateRandomEvent(player));
        cardL.show(eventGui, "EVENT");
    }
    public void newPlayer(String name){
        main.newPlayer(name);
        nextEncounter();
    }
    
    
    public void gotToOutCome(BridgingEvent outCome){
        outComeGui.setOutCome(outCome);
        cardL.show(mainPanel, "OUTCOME");
    }
    private void loadSaves(){
        main.loadSaves();
    }
    public ArrayList<String[][]> getSaves(){
        return main.getSaves();
    }
    public void gotToLoad(){
        loadSaves();
        loadGameGui.loadOptions();
        cardL.show(mainPanel, "LOAD");
    }
    public void gotToMainMenu(){
        cardL.show(mainPanel, "MAIN");
    }
    
    public void loadGame(int index){
        main.setPlayer(index);
        eventGui.setNextEncounter(main.getSavedEncounter());
        cardL.show(mainPanel, "EVENT");
        
    }
    public void setCurrentEncounter(EncounterEvent e){
        main.setCurrentEncounter(e);
    }
    public void save(){
        main.save();
    }
    
    
}
