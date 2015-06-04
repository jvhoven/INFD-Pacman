/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spel.spelelementen;

import java.awt.Color;
import java.awt.Graphics2D;
import spel.interfaces.Eetbaar;
import spel.interfaces.Inhoud;
import spel.levelelementen.LeegVakje;
import spel.levelelementen.Vakje;

/**
 *
 * @author Jeffrey
 */
public class Bolletje implements Eetbaar, Inhoud {
    
    LeegVakje huidigVakje;
    
    public Bolletje(LeegVakje startVakje){
        this.huidigVakje = startVakje;
    }
    
    @Override
    public void teken(Graphics2D g) {
        if (huidigVakje != null) {
            int x = huidigVakje.positie.x * Vakje.SIZE - Vakje.SIZE + 5;
            int y = huidigVakje.positie.y * Vakje.SIZE - Vakje.SIZE + 5;
            
            g.setColor(new Color(101, 109, 120));
            g.fillOval(x + 35, y + 35, 20, 20);
        }
    }

    @Override
    public int opeten() {
        System.out.println("Ik ben opgegeten!");
        return 10;
    }
}
