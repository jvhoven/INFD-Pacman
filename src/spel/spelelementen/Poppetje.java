package spel.spelelementen;

import java.awt.Graphics2D;
import javax.swing.Timer;
import spel.Speelveld;
import spel.levelelementen.LeegVakje;

public abstract class Poppetje extends SpelElement {
    
    protected Speelveld speelveld;
    protected LeegVakje startVakje;
    protected Timer beweegTimer;
    
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
    
    public LeegVakje getHuidigVakje() {
        return this.huidigVakje;
    }
    
    public void setStartVakje(LeegVakje vakje) {
        this.startVakje = vakje;
    }
    
    public LeegVakje getStartVakje(){
        return this.startVakje;
    }
}

