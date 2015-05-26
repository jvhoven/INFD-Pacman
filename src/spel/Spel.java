package spel;


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
    
    final int BREEDTE = 550;
    final int HOOGTE = 550;
    
    public Spel() {
        super("INF-D Pacman Groep B6");
        
        setSize(BREEDTE, HOOGTE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initialiseer();
    }

    private void initialiseer() {
        
        Speelveld speelveld = new Speelveld();
        GUI gui = new GUI();
        
        add(gui);
        add(speelveld);
        
        setVisible(true);
    }
    
    
    
}
