/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spel.levelelementen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import spel.interfaces.Eetbaar;
import spel.interfaces.Inhoud;
import spel.spelelementen.Poppetje;

/**
 *
 * @author Hans
 */
public class LeegVakje extends Vakje {

    ArrayList<Inhoud> elementen;

    public LeegVakje(Positie positie) {
        this.positie = positie;
        this.elementen = new ArrayList();        
        this.buurVakjes = new HashMap<>();
    }

    public void verwijderInhoud(Inhoud p) {
        if (this.elementen.contains(p)) {
            this.elementen.remove(p);
        }
    }

    public void toevoegenInhoud(Inhoud p) {
        if (!this.elementen.contains(p)) {
            this.elementen.add(p);
            
            if(p instanceof Poppetje) {
                Poppetje poppetje = (Poppetje) p;
                poppetje.setHuidigVakje(this);
            }
        }
    }
    
    public Inhoud getInhoud() {
        for(Inhoud inhoud : elementen) {
            if(inhoud instanceof Eetbaar) {
                return inhoud;
            }
        }
        
        return null;
    }

    @Override
    public void teken(Graphics2D g) {
        g.setColor(Color.white);

        int startPositieX = positie.x * SIZE - SIZE + 1;
        int startPositieY = positie.y * SIZE - SIZE + 1;

        g.fillRect(startPositieX, startPositieY, SIZE - 2, SIZE - 2);
        
        g.setColor(Color.black);
        g.drawString(positie.y + ", " + positie.x, startPositieX + 10, startPositieY + 10);
        
        for (Inhoud p : this.elementen) {
            p.teken(g);
        }
    }

}
