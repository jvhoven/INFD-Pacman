/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spel.levelelementen;

import java.awt.Graphics2D;
import java.util.HashMap;
import spel.Richting;
import spel.Speelveld;

/**
 *
 * @author Hans
 */
public abstract class Vakje {

    public static final int SIZE = 100;

    public Positie positie;
    public HashMap<Richting, Vakje> buurVakjes;
    
    public Vakje getBuurVakje(Richting richting) {
        return buurVakjes.get(richting);
    }

    public void setBuurVakjes(Vakje[][] level) {
        if (this.positie.x > 1) {
            this.buurVakjes.put(Richting.LINKS, level[this.positie.x - 2][this.positie.y - 1]);
        }

        if (this.positie.y > 1) {
            this.buurVakjes.put(Richting.OMHOOG, level[this.positie.x - 1][this.positie.y - 2]);
        }

        if (this.positie.x < 5) {
            this.buurVakjes.put(Richting.RECHTS, level[this.positie.x][this.positie.y - 1]);
        }

        if (this.positie.y < 5) {
            this.buurVakjes.put(Richting.OMLAAG, level[this.positie.x - 1][this.positie.y]);
        }

    }

    public abstract void teken(Graphics2D g);
}
