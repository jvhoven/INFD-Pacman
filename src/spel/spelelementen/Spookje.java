package spel.spelelementen;

import spel.AIs.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import spel.Speelveld;
import spel.enums.Afbeelding;
import spel.interfaces.Eetbaar;

public class Spookje extends Poppetje implements Eetbaar {
    
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

