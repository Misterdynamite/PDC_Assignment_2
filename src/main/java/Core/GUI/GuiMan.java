/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.GUI;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.aut604.Main.Main;

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
        mainPanel.add(LoadGame(this), "LOAD");
        EventGui eventGui = new EventGui(this);
        mainPanel.add(eventGui, "EVENT");
        mainPanel.add(new OutCome(eventGui), "OUTCOME");
        
        
        
        
    }
    
    public Event getNextEvent(){
        return main.getNextEvent();
    }
    
    
}
