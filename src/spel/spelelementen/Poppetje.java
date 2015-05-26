/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spel.spelelementen;

import java.awt.Graphics2D;
import javax.swing.JComponent;
import spel.levelelementen.LeegVakje;

/**
 *
 * @author Hans
 */
public abstract class Poppetje{
    protected LeegVakje huidigVakje;
    
    public abstract void teken(Graphics2D g);
    
    public void beweegNaar(LeegVakje vakje){
        this.huidigVakje.verwijderPoppetje(this);
        vakje.toevoegenPoppetje(this);
    }
    
    public void setHuidigVakje(LeegVakje vakje){
        this.huidigVakje = vakje;
    }
}
