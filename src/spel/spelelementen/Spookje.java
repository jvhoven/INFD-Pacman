/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spel.spelelementen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import spel.Speelveld;
import spel.enums.Afbeelding;
import spel.interfaces.Eetbaar;
import spel.levelelementen.LeegVakje;
import spel.levelelementen.Vakje;

/**
 *
 * @author Hans
 */
public class Spookje extends Poppetje implements Eetbaar{

    private BufferedImage spookPlaatje = null;

    public Spookje(Speelveld speelveld, LeegVakje startVakje){
        this.speelveld = speelveld;
        setHuidigVakje(startVakje);

        Afbeelding afbeelding = Afbeelding.SPOOK_BLAUW;
        spookPlaatje = afbeelding.getAfbeelding();
    }
    
    public Spookje(Speelveld speelveld, LeegVakje startVakje, Afbeelding afbeelding) {
        this.speelveld = speelveld;
        setHuidigVakje(startVakje);
        spookPlaatje = afbeelding.getAfbeelding();
    }

    @Override
    public void teken(Graphics2D g) {

        if (huidigVakje != null) {
            int x = huidigVakje.positie.x * Vakje.SIZE - (Vakje.SIZE);
            int y = huidigVakje.positie.y * Vakje.SIZE - (Vakje.SIZE);
            if (spookPlaatje == null) {
                g.setColor(Color.blue);
                g.fillOval(x, y, Vakje.SIZE - 10, Vakje.SIZE - 10);
            } else {                
                
                int width = spookPlaatje.getWidth();
                int height = spookPlaatje.getHeight();
                
                g.drawImage(spookPlaatje, x, y, Vakje.SIZE, Vakje.SIZE, null);
            }
        }
    }

    @Override
    public int opeten() {
        this.huidigVakje.verwijderInhoud(this);
        return getPunten();
    }

    @Override
    public int getPunten() {
        return 200;
    }

}
