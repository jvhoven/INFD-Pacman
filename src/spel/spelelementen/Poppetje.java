package spel.spelelementen;

import java.awt.Graphics2D;
import spel.Speelveld;
import spel.interfaces.SpelElement;
import spel.levelelementen.LeegVakje;

public abstract class Poppetje implements SpelElement {
    
    protected Speelveld speelveld;
    protected LeegVakje huidigVakje;

    @Override
    public abstract void teken(Graphics2D g);

    public void beweegNaar(LeegVakje vakje) {
        this.huidigVakje.verwijderInhoud(this);
        vakje.toevoegenSpelElement(this);
        this.speelveld.repaint();
    }

    public void setHuidigVakje(LeegVakje vakje) {
        this.huidigVakje = vakje;
    }

    public LeegVakje getHuidigVakje() {
        return this.huidigVakje;
    }
}

