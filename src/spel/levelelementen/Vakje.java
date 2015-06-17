package spel.levelelementen;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import spel.enums.Richting;
import spel.spelelementen.Spookje;

public abstract class Vakje {

    public static final int SIZE = 43;
    public Positie positie;
    public HashMap<Richting, Vakje> buurVakjes;

    public Vakje getBuurVakje(Richting richting) {
        return this.buurVakjes.get(richting);
    }

    public ArrayList<LeegVakje> getLegeBuurVakjes() {
        ArrayList<Vakje> vakjes = new ArrayList<>(this.buurVakjes.values());
        ArrayList<LeegVakje> legeVakjes = new ArrayList<>();
        for (Vakje vakje : vakjes) {
            if (!(vakje instanceof LeegVakje)) {
                continue;
            }
            legeVakjes.add((LeegVakje) vakje);
        }
        return legeVakjes;
    }

    public ArrayList<LeegVakje> getLegeBuurVakjesZonderSpookje(Spookje uitzondering) {
        ArrayList<LeegVakje> legeVakjes = getLegeBuurVakjes();
        ArrayList<LeegVakje> legeVakjesZonderSpookje = new ArrayList<>();
        for (LeegVakje vakje : legeVakjes) {
            Spookje spookje = vakje.getSpookje();
            if (spookje == null || spookje == uitzondering) {
                legeVakjesZonderSpookje.add(vakje);
            }
        }
        return legeVakjesZonderSpookje;
    }

    public void setBuurVakjes(Vakje[][] level, int LEVEL_SIZE) {
        if (this.positie.x > 1) {
            this.buurVakjes.put(Richting.LINKS, level[this.positie.x - 2][this.positie.y - 1]);
        }
        if (this.positie.y > 1) {
            this.buurVakjes.put(Richting.OMHOOG, level[this.positie.x - 1][this.positie.y - 2]);
        }
        if (this.positie.x < LEVEL_SIZE) {
            this.buurVakjes.put(Richting.RECHTS, level[this.positie.x][this.positie.y - 1]);
        }
        if (this.positie.y < LEVEL_SIZE) {
            this.buurVakjes.put(Richting.OMLAAG, level[this.positie.x - 1][this.positie.y]);
        }
    }

    public abstract void teken(Graphics2D var1);
}
