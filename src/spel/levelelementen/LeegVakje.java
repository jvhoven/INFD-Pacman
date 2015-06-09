package spel.levelelementen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import spel.interfaces.Eetbaar;
import spel.interfaces.SpelElement;
import spel.spelelementen.Pacman;
import spel.spelelementen.Poppetje;

public class LeegVakje extends Vakje {
    
    ArrayList<SpelElement> spelElementen;

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
                Poppetje poppetje = (Poppetje)spelElement;
                poppetje.setHuidigVakje(this);
            }
        }
    }

    public ArrayList<Eetbaar> getEetbareSpelElementen() {
        ArrayList<Eetbaar> eetbareElementen = new ArrayList<Eetbaar>();
        for (SpelElement e : this.spelElementen) {
            if (!(e instanceof Eetbaar)) continue;
            eetbareElementen.add((Eetbaar)e);
        }
        return eetbareElementen;
    }
    
    public Pacman getPacman() {
        for(SpelElement element : this.spelElementen) {
            if(element instanceof Poppetje) {
                if((Poppetje) element instanceof Pacman) {
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
     
        for (SpelElement p : this.spelElementen) {
            p.teken(g);
        }
    }
}

