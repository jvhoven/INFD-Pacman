package spel;


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
    
    final int BREEDTE = 516;
    final int HOOGTE = 574;
    
    Speelveld speelveld = null;
    GUI gui = null;
    
    public Spel() {
        super("INF-D Pacman Groep B6");
        
        setSize(BREEDTE, HOOGTE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        initialiseer();
    }

    private void initialiseer() {
        
        gui = new GUI(this);        
        add(gui, BorderLayout.NORTH);
        
        speelveld = new Speelveld();
        add(speelveld, BorderLayout.CENTER);        
        
        setVisible(true);
    }
    
    public String changeSpelStatus(){
        if(speelveld.getSpelStatus() == SpelStatus.GESTART){
            speelveld.pauzeerSpel();
            return "Hervat";
        } else {
            speelveld.startSpel();
            return "Pauzeer";
        }
    }    
    
    public void resetSpel(){
        speelveld.reset();
    }
}
