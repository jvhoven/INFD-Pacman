/*
 * Decompiled with CFR 0_101.
 */
package spel.levelelementen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import spel.levelelementen.Positie;
import spel.levelelementen.Vakje;

public class Muur
extends Vakje {
    public Muur(Positie positie) {
        this.positie = positie;
        this.buurVakjes = new HashMap();
    }

    @Override
    public void teken(Graphics2D g) {
        g.setColor(new Color(102, 153, 255));
        int startPositieX = this.positie.x * 43 - 43 + 1;
        int startPositieY = this.positie.y * 43 - 43 + 1;
        g.fillRect(startPositieX, startPositieY, 41, 41);
    }
}

