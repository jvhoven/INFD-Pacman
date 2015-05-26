/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spel.levelelementen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import spel.spelelementen.Poppetje;

/**
 *
 * @author Hans
 */
public class LeegVakje extends Vakje {

    ArrayList<Poppetje> poppetjes;

    public LeegVakje(Positie positie) {
        this.positie = positie;
        this.poppetjes = new ArrayList();        
        this.buurVakjes = new ArrayList<>();
    }

    public void verwijderPoppetje(Poppetje p) {
        if (this.poppetjes.contains(p)) {
            this.poppetjes.remove(p);
        }
    }

    public void toevoegenPoppetje(Poppetje p) {
        if (!this.poppetjes.contains(p)) {
            this.poppetjes.add(p);
            p.setHuidigVakje(this);
        }
    }

    @Override
    public void teken(Graphics2D g) {
        g.setColor(Color.white);

        int startPositieX = positie.x * SIZE - SIZE + 1;
        int startPositieY = positie.y * SIZE - SIZE + 1;

        g.fillRect(startPositieX, startPositieY, SIZE - 2, SIZE - 2);
        
        g.setColor(Color.black);
        g.drawString(positie.x + ", " + positie.y, startPositieX + 10, startPositieY + 10);
        
        for (Poppetje p : this.poppetjes) {
            p.teken(g);
        }
    }

}
