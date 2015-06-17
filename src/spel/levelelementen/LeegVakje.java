package spel.levelelementen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import spel.interfaces.Eetbaar;
import spel.spelelementen.SpelElement;
import spel.spelelementen.Bolletje;
import spel.spelelementen.Pacman;
import spel.spelelementen.Poppetje;
import spel.spelelementen.Spookje;

public class LeegVakje extends Vakje implements Comparable {

    ArrayList<SpelElement> spelElementen;

    //dijkstra>
    private int tempAfstand = 0;

    public void setTempAfstand(int afstand) {
        this.tempAfstand = afstand;
    }

    public int getTempAfstand() {
        return this.tempAfstand;
    }
    //<dijkstra

    //Astar>
    public LeegVakje parent;
    public int H;
    public int F;
    public int G;
    //<Astar

    public LeegVakje(Positie positie) {
        this.positie = positie;
        this.spelElementen = new ArrayList();
        this.buurVakjes = new HashMap();
    }

    public void verwijderSpelElement(SpelElement spelElement) {
        if (this.spelElementen.contains(spelElement)) {
            this.spelElementen.remove(spelElement);
        }
    }

    public void toevoegenSpelElement(SpelElement spelElement) {
        if (!this.spelElementen.contains(spelElement)) {
            this.spelElementen.add(spelElement);
            if (spelElement instanceof Poppetje) {
                Poppetje poppetje = (Poppetje) spelElement;
                poppetje.setHuidigVakje(this);
            }
        }
    }

    public ArrayList<Eetbaar> getEetbareSpelElementen() {
        ArrayList<Eetbaar> eetbareElementen = new ArrayList<Eetbaar>();
        for (SpelElement e : this.spelElementen) {
            if (!(e instanceof Eetbaar)) {
                continue;
            }
            eetbareElementen.add((Eetbaar) e);
        }
        return eetbareElementen;
    }

    public Spookje getSpookje() {
        ArrayList<Eetbaar> eetbareElementen = getEetbareSpelElementen();
        Iterator iterator = eetbareElementen.iterator();
        while (iterator.hasNext()) {
            Eetbaar e = (Eetbaar) iterator.next();
            if (e instanceof Spookje) {
                return (Spookje) e;
            }
        }
        return null;
    }

    public Bolletje getBolletje() {
        ArrayList<Eetbaar> eetbareElementen = getEetbareSpelElementen();
        for (int i = 0; i < eetbareElementen.size(); i++) {
            Eetbaar e = eetbareElementen.get(i);
            if (e instanceof Bolletje) {
                return (Bolletje) e;
            }
        }
        return null;
    }

    public Pacman getPacman() {
        for (SpelElement element : this.spelElementen) {
            if (element instanceof Poppetje) {
                if ((Poppetje) element instanceof Pacman) {
                    return (Pacman) element;
                }
            }
        }

        return null;
    }

    @Override
    public void teken(Graphics2D g) {
        g.setColor(Color.white);
        int startPositieX = this.positie.x * 43 - 43 + 1;
        int startPositieY = this.positie.y * 43 - 43 + 1;
        g.fillRect(startPositieX, startPositieY, 41, 41);
        g.setColor(Color.black);

        g.drawString(positie.x + ", " + positie.y, startPositieX + 10, startPositieY + 10);

        for (SpelElement p : this.spelElementen) {
            p.teken(g);
        }
    }

    @Override
    public int compareTo(Object o) {
        /*voor dijkstra algoritme
         if (o instanceof LeegVakje) {
         LeegVakje leegVakje = (LeegVakje) o;
         if (this.tempAfstand < leegVakje.getTempAfstand()) {
         return 0;
         }
         }
         */

        if (o instanceof LeegVakje) {
            LeegVakje leegVakje = (LeegVakje) o;
            if (this.F < leegVakje.F) {
                return 0;
            }
        }
        return 1;
    }

    public String toString() {
        return "(" + this.positie.x + " " + this.positie.y + ")";
    }
}
