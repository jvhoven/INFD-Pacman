package spel.spelelementen;

import java.awt.Color;
import java.awt.Graphics2D;
import spel.interfaces.Eetbaar;
import spel.interfaces.SpelElement;
import spel.levelelementen.LeegVakje;

public class Superbolletje implements Eetbaar, SpelElement {
    
    LeegVakje huidigVakje;

    public Superbolletje(LeegVakje startVakje) {
         this.huidigVakje = startVakje;
    }

    @Override
    public void teken(Graphics2D g) {
        if (this.huidigVakje != null) {
            int x = this.huidigVakje.positie.x * 43 - 43 + 14;
            int y = this.huidigVakje.positie.y * 43 - 43 + 14;
            g.setColor(new Color(0, 255, 0));
            g.fillOval(x, y, 14, 14);
        }
    }

    @Override
    public int opeten() {
        this.huidigVakje.verwijderSpelElement(this);
        return this.getPunten();
    }

    @Override
    public int getPunten() {
        return 10;
    }
    
}
