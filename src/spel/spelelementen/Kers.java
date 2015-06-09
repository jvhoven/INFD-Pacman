package spel.spelelementen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import spel.enums.Afbeelding;
import spel.interfaces.Eetbaar;
import spel.interfaces.SpelElement;
import spel.levelelementen.LeegVakje;

public class Kers implements Eetbaar, SpelElement {
    
    LeegVakje huidigVakje;
    BufferedImage afbeelding = null;

    public Kers(LeegVakje startVakje) {
        this.huidigVakje = startVakje;
        this.afbeelding = Afbeelding.KERS.getAfbeelding();
    }

    @Override
    public void teken(Graphics2D g) {
        if (this.huidigVakje != null) {
            int x = this.huidigVakje.positie.x * 43 - 40;
            int y = this.huidigVakje.positie.y * 43 - 40;
            
            if (this.afbeelding == null) {
                g.setColor(new Color(0, 255, 0));
                g.fillOval(x, y, 14, 14);
            } else {
                g.drawImage(afbeelding, x, y, 30, 30, null);
            }
        }
    }

    @Override
    public int opeten() {
        this.huidigVakje.verwijderSpelElement(this);
        return this.getPunten();
    }

    @Override
    public int getPunten() {
        return 100;
    }
    
}
