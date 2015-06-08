/*
 * Decompiled with CFR 0_101.
 */
package spel.spelelementen;

import AIs.AI;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import spel.Speelveld;
import spel.enums.Afbeelding;
import spel.interfaces.Eetbaar;
import spel.interfaces.SpelElement;
import spel.levelelementen.LeegVakje;
import spel.levelelementen.Positie;
import spel.spelelementen.Poppetje;

public class Spookje
extends Poppetje
implements Eetbaar {
    private BufferedImage spookPlaatje = null;
    private AI ai = null;

    public Spookje(Speelveld speelveld, Afbeelding afbeelding) {
        this.speelveld = speelveld;
        this.spookPlaatje = afbeelding.getAfbeelding();
    }

    @Override
    public void teken(Graphics2D g) {
        if (this.huidigVakje != null) {
            int x = this.huidigVakje.positie.x * 43 - 43;
            int y = this.huidigVakje.positie.y * 43 - 43;
            if (this.spookPlaatje == null) {
                g.setColor(Color.blue);
                g.fillOval(x, y, 33, 33);
            } else {
                int width = this.spookPlaatje.getWidth();
                int height = this.spookPlaatje.getHeight();
                g.drawImage(this.spookPlaatje, x, y, 43, 43, null);
            }
        }
    }

    @Override
    public int opeten() {
        this.huidigVakje.verwijderInhoud(this);
        return this.getPunten();
    }

    @Override
    public int getPunten() {
        return 200;
    }

    public void setAI(AI ai) {
        this.ai = ai;
    }

    public void startAI() {
        if (this.ai != null) {
            this.ai.start();
        }
    }

    public void stopAI() {
        if (this.ai != null) {
            this.ai.stop();
        }
    }
}

