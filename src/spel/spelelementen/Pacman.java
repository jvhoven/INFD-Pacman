/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spel.spelelementen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import spel.levelelementen.LeegVakje;
import spel.levelelementen.Positie;
import spel.levelelementen.Vakje;

/**
 *
 * @author Hans
 */
public class Pacman extends Poppetje implements KeyListener {
    
    private boolean arrowKeyPressed = false;
    
    public Pacman(LeegVakje startVakje) {
        this.huidigVakje = startVakje;
    }

    @Override
    public void teken(Graphics2D g) {
        g.setColor(Color.yellow);

        if (huidigVakje != null) {
            int x = huidigVakje.positie.x * Vakje.SIZE - Vakje.SIZE + 5;
            int y = huidigVakje.positie.y * Vakje.SIZE - Vakje.SIZE + 5;

            g.fillOval(x, y, Vakje.SIZE - 10, Vakje.SIZE - 10);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    private void beweeg(Positie buurPositie) {
        Vakje buurVakje = this.huidigVakje.getBuurVakje(buurPositie);

        if (buurVakje != null && buurVakje instanceof LeegVakje) {
            this.beweegNaar((LeegVakje) buurVakje);
        }
    }
    
    private Positie getPositie(KeyEvent e) {
        
        Positie buurPositie = null;
        Positie huidigePositie = this.huidigVakje.positie;
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                buurPositie = new Positie(huidigePositie.x, huidigePositie.y + 1);
                break;
            case KeyEvent.VK_UP:
                buurPositie = new Positie(huidigePositie.x, huidigePositie.y - 1);
                break;
            case KeyEvent.VK_LEFT:
                buurPositie = new Positie(huidigePositie.x - 1, huidigePositie.y);
                break;
            case KeyEvent.VK_RIGHT:
                buurPositie = new Positie(huidigePositie.x + 1, huidigePositie.y);
                break;
        }
        
        return buurPositie;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (!arrowKeyPressed) {
            Positie buurPositie = getPositie(e);
           
            if (buurPositie != null) {
                this.beweeg(buurPositie);
            }
        }
        arrowKeyPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        arrowKeyPressed = false;
    }
}
