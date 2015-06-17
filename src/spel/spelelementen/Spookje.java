package spel.spelelementen;

import spel.AIs.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;
import spel.Speelveld;
import spel.enums.Afbeelding;
import spel.interfaces.Eetbaar;
import spel.levelelementen.LeegVakje;

public class Spookje extends Poppetje implements Eetbaar {

    private BufferedImage spookPlaatje = null;
    private AI ai = null;
    private LeegVakje vorigVakje;
    private boolean isEetBaar = false;
    private Timer eetbaarTimer = null;
    
    public Spookje(Speelveld speelveld, Afbeelding afbeelding) {
        this.speelveld = speelveld;
        this.spookPlaatje = afbeelding.getAfbeelding();
        
        this.eetbaarTimer = new Timer(10000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Spookje.this.eetbaarTimer.stop();
                Spookje.this.isEetBaar = false;
            }
        });
    }
    
     /*
    * Test constructor gebruikt voor Unit tests
    */
    public Spookje(LeegVakje startPositie) {
        this.startVakje = startPositie;
    }
    
    public boolean getIsEetbaar(){
        return this.isEetBaar;
    }
    
    public void maakEetbaar(){
        this.isEetBaar = true;
        this.eetbaarTimer.start();        
    }

    public LeegVakje getVorigVakje() {
        return this.vorigVakje;
    }

    public void setVorigVakje(LeegVakje vakje) {
        this.vorigVakje = vakje;
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
    public void beweegNaar(LeegVakje vakje) {

        super.beweegNaar(vakje);

        // Als spookje tegen pacman aan "loopt"
        Pacman pacman = vakje.getPacman();
        if (pacman != null) {
            if (!this.isEetBaar) {
                pacman.verwijderLeven();
            } else {
                pacman.etenEetbaarSpelElement(this);
            }
        }
    }

    @Override
    public int opeten() {
        this.reset();
        this.isEetBaar = false;
        
        if (ai != null) {
            ai.pauzeer(1500);
        }

        return this.getPunten();
    }
    
    @Override
    public void reset(){
        super.reset();
        ai.pauzeer(1500);
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
