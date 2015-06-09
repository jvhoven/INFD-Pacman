package spel.spelelementen;

import java.awt.Graphics2D;
import spel.Speelveld;
import spel.interfaces.SpelElement;
import spel.levelelementen.LeegVakje;

public abstract class Poppetje implements SpelElement {
    
    protected Speelveld speelveld;
    protected LeegVakje huidigVakje;
    protected LeegVakje startVakje;

    @Override
    public abstract void teken(Graphics2D g);
    
    public void reset() {
        beweegNaar(startVakje);
    }
    
    public void beweegNaar(LeegVakje vakje) {
        this.huidigVakje.verwijderSpelElement(this);
        vakje.toevoegenSpelElement(this);
        this.speelveld.repaint();
    }

    public void setHuidigVakje(LeegVakje vakje) {
        this.huidigVakje = vakje;
    }
    
    public void setStartVakje(LeegVakje vakje) {
        this.startVakje = vakje;
    }

    public LeegVakje getHuidigVakje() {
        return this.huidigVakje;
    }
}

