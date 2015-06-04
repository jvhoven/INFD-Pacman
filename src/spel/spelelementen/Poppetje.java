/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spel.spelelementen;

import java.awt.Graphics2D;
import spel.Speelveld;
import spel.interfaces.Inhoud;
import spel.levelelementen.LeegVakje;

/**
 *
 * @author Hans
 */
public abstract class Poppetje implements Inhoud{
    protected Speelveld speelveld;
    protected LeegVakje huidigVakje;
    
    @Override
    public abstract void teken(Graphics2D g);
    
    public void beweegNaar(LeegVakje vakje){
        this.huidigVakje.verwijderInhoud(this);
        vakje.toevoegenInhoud(this);
        speelveld.repaint();
    }
    
    public void setHuidigVakje(LeegVakje vakje){
        this.huidigVakje = vakje;
    }
}
