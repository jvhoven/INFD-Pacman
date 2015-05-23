package spel;


import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jeffrey
 */
public class Speelveld extends JPanel implements KeyListener {
    
    SpelStatus status;
    Graphics2D g;
    
    public Speelveld() {
        super();
        
        setPreferredSize(new Dimension(500, 500));
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
        
        start();
    }
    
    private void initialiseer() {
        status = SpelStatus.GESTART;
        g = (Graphics2D) getGraphics();
    }
    
    private void start() {
        
        initialiseer();
       // Game loop komt hier
    }
    
    private void teken() {
        
    }
 
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
