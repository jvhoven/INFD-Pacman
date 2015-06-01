/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spel.levelelementen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;

/**
 *
 * @author Hans
 */
public class Muur extends Vakje {

    public Muur(Positie positie){
        this.positie = positie;
        this.buurVakjes = new HashMap<>();
    }
    
    @Override
    public void teken(Graphics2D g) {
        g.setColor(new Color(102, 153, 255));

        int startPositieX = positie.x * SIZE - SIZE + 1;
        int startPositieY = positie.y * SIZE - SIZE + 1;

        g.fillRect(startPositieX, startPositieY, SIZE - 2, SIZE - 2);
    }
    
}
