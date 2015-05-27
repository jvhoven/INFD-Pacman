/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spel.spelelementen;

import java.awt.Color;
import java.awt.Graphics2D;
import spel.levelelementen.LeegVakje;
import spel.levelelementen.Vakje;

/**
 *
 * @author Hans
 */
public class Spookje extends Poppetje {
    
    public Spookje(Vakje startVakje) {
        setHuidigVakje((LeegVakje)startVakje);
    }
    
    @Override
    public void teken(Graphics2D g) {
        g.setColor(Color.blue);

        if (huidigVakje != null) {
            int x = huidigVakje.positie.x * Vakje.SIZE - Vakje.SIZE + 5;
            int y = huidigVakje.positie.y * Vakje.SIZE - Vakje.SIZE + 5;

            g.fillOval(x, y, Vakje.SIZE - 10, Vakje.SIZE - 10);
        }
    }
    
}
