package spel;


import spel.enums.SpelStatus;
import java.awt.BorderLayout;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jeffrey
 */
public class Spel extends JFrame {
    
    private final int BREEDTE = 516;
    private final int HOOGTE = 574;
    
    Speelveld speelveld = null;
    GUI gui = null;
    
    public Spel() {
        super("INF-D Pacman Groep B6");
        
        setSize(BREEDTE, HOOGTE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initialiseer();
    }

    private void initialiseer() {
        
        gui = new GUI(this);        
        add(gui, BorderLayout.NORTH);
        
        speelveld = new Speelveld(this);
        add(speelveld, BorderLayout.CENTER);        
        
        setVisible(true);
    }
    
    public String setSpelStatus(){
        if(speelveld.getSpelStatus() == SpelStatus.GESTART){
            speelveld.pauzeer();
            return "Hervat";
        } else {
            speelveld.start();
            return "Pauzeer";
        }
    }    
    
    public void reset(){
        speelveld.reset();        
    }
    
    public void showScore(int huidigeScore){
        this.gui.scoreLabel.setText("Score: " + Integer.toString(huidigeScore));
    }
}
