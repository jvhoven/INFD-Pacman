/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spel.levelelementen;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Hans
 */
public abstract class Vakje {

    public static final int SIZE = 100;

    public Positie positie;
    public ArrayList<Vakje> buurVakjes;

    public Vakje getBuurVakje(Positie buurPositie) {
        for (Vakje v : buurVakjes) {
            if (v.positie.equals(buurPositie)) {
                return v;
            }
        }
        return null;
    }

    public void setBuurVakjes(Vakje[][] level) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                Positie buurPositie = new Positie(this.positie.x + i, this.positie.y + j);
                if ((buurPositie.x == this.positie.x || buurPositie.y == this.positie.y)
                        && !(buurPositie.x == this.positie.x && buurPositie.y == this.positie.y)
                        && buurPositie.x >= 1 && buurPositie.x <= 5 && buurPositie.y >= 1 && buurPositie.y <= 5) {
                    buurVakjes.add(level[buurPositie.x - 1][buurPositie.y - 1]);
                }
            }
        }
    }

    public abstract void teken(Graphics2D g);
}
