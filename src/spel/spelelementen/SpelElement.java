package spel.spelelementen;

import java.awt.Graphics2D;
import spel.levelelementen.LeegVakje;

public abstract class SpelElement {
    protected LeegVakje huidigVakje;
    
    public abstract void teken(Graphics2D g);
}

