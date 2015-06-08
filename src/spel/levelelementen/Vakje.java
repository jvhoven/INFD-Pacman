/*
 * Decompiled with CFR 0_101.
 */
package spel.levelelementen;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import spel.enums.Richting;
import spel.levelelementen.LeegVakje;
import spel.levelelementen.Positie;

public abstract class Vakje {
    public static final int SIZE = 43;
    public Positie positie;
    public HashMap<Richting, Vakje> buurVakjes;

    public Vakje getBuurVakje(Richting richting) {
        return this.buurVakjes.get((Object)richting);
    }

    public ArrayList<LeegVakje> getLegeBuurVakjes() {
        ArrayList<Vakje> vakjes = new ArrayList<Vakje>(this.buurVakjes.values());
        ArrayList<LeegVakje> legeVakjes = new ArrayList<LeegVakje>();
        for (Vakje vakje : vakjes) {
            if (!(vakje instanceof LeegVakje)) continue;
            legeVakjes.add((LeegVakje)vakje);
        }
        return legeVakjes;
    }

    public void setBuurVakjes(Vakje[][] level) {
        if (this.positie.x > 1) {
            this.buurVakjes.put(Richting.LINKS, level[this.positie.x - 2][this.positie.y - 1]);
        }
        if (this.positie.y > 1) {
            this.buurVakjes.put(Richting.OMHOOG, level[this.positie.x - 1][this.positie.y - 2]);
        }
        if (this.positie.x < 15) {
            this.buurVakjes.put(Richting.RECHTS, level[this.positie.x][this.positie.y - 1]);
        }
        if (this.positie.y < 15) {
            this.buurVakjes.put(Richting.OMLAAG, level[this.positie.x - 1][this.positie.y]);
        }
    }

    public abstract void teken(Graphics2D var1);
}

