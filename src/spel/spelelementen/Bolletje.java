/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spel.spelelementen;

import java.awt.Color;
import java.awt.Graphics2D;
import spel.interfaces.Eetbaar;
import spel.interfaces.Inhoud;
import spel.levelelementen.LeegVakje;
import spel.levelelementen.Vakje;

/**
 *
 * @author Jeffrey
 */
public class Bolletje implements Eetbaar, Inhoud {

    LeegVakje huidigVakje;

    public Bolletje(LeegVakje startVakje) {
        this.huidigVakje = startVakje;
    }

    @Override
    public void teken(Graphics2D g) {
        if (huidigVakje != null) {
               
            System.out.println("X: " + huidigVakje.positie.x + " Y: " + huidigVakje.positie.y);
            
            int x = huidigVakje.positie.x * Vakje.SIZE;
            int y = huidigVakje.positie.y * Vakje.SIZE;

            g.setColor(new Color(101, 109, 120));
            g.fillOval(x, y, 20, 20);
        }
    }

    @Override
    public int opeten() {
        this.huidigVakje.verwijderInhoud(this);
        return getPunten();
    }

    @Override
    public int getPunten() {
        return 10;
    }
}