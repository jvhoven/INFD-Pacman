package spel.spelelementen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.Timer;
import spel.Speelveld;
import spel.enums.Afbeelding;
import spel.enums.Richting;
import spel.interfaces.Eetbaar;
import spel.levelelementen.LeegVakje;
import spel.levelelementen.Vakje;

public class Pacman extends Poppetje implements KeyListener {
    
    private boolean arrowKeyPressed = false;
    private BufferedImage pacmanPlaatje = null;
    private Richting richting;
    private int levens = 0;
    private boolean isImmuun = false;
    private Timer immuunTimer = null;

    public Vakje startPositie = null;
    
    public int getLevens() {
        return this.levens;
    }

    public void verwijderLeven() {
        --this.levens;
        if (this.levens > 0) {
            this.speelveld.resetPositiePoppetjes();
        }
    }

    public Pacman(Speelveld speelveld) {
        this.speelveld = speelveld;
        this.richting = Richting.NEUTRAAL;
        this.levens = 3;
        this.immuunTimer = new Timer(10000, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Pacman.this.immuunTimer.stop();
                Pacman.this.isImmuun = false;
            }
        });
        this.pacmanPlaatje = Afbeelding.PACMAN.getAfbeelding();
    }

    @Override
    public void teken(Graphics2D g) {
        if (this.huidigVakje != null) {
            
            int x = this.huidigVakje.positie.x * 43 - 43;
            int y = this.huidigVakje.positie.y * 43 - 43;
            
            if (this.pacmanPlaatje == null) {
                g.setColor(Color.yellow);
                g.fillOval(x, y, 33, 33);
            } else {
                BufferedImage afbeelding = this.setOrientatie();
                g.drawImage(afbeelding, x, y, 43, 43, null);
            }
        }
    }

    private BufferedImage setOrientatie() {
        int width = this.pacmanPlaatje.getWidth() / 2;
        int height = this.pacmanPlaatje.getHeight() / 2;
        
        BufferedImage pacmanOrientatie = new BufferedImage(width, height, 2);
        Graphics2D bg = pacmanOrientatie.createGraphics();
        
        bg.rotate(this.richting.getGraden(), width / 2, height / 2);
        bg.drawImage(this.pacmanPlaatje, 0, 0, width, height, null);
        bg.dispose();
        
        return pacmanOrientatie;
    }

    private void probeerTeBewegen(Richting richting) {
        Vakje buurVakje = this.huidigVakje.getBuurVakje(richting);
        if (buurVakje != null && buurVakje instanceof LeegVakje) {
            LeegVakje target = (LeegVakje)buurVakje;
            this.beweegNaar(target);
            this.verwerkInhoudHuidigVakje();
        }
    }
    
    public void verwerkInhoudHuidigVakje() {
        
        ArrayList<Eetbaar> eetbareInhoud = this.huidigVakje.getEetbareSpelElementen();
        
        for (Eetbaar e : eetbareInhoud) {
            int punten;
            if (e instanceof Spookje) {
                if (this.isImmuun) {
                    punten = e.opeten();
                    this.speelveld.addPunten(punten);
                } else {
                    this.verwijderLeven();
                }
            } else if(e instanceof Superbolletje) {
                immuunTimer.start();
                isImmuun = true;
            } else {
                punten = e.opeten();
                this.speelveld.addPunten(punten);
            }
        }
    }

    private void setRichting(KeyEvent e) {
        switch (e.getKeyCode()) {
            
            case KeyEvent.VK_DOWN:
                this.richting = Richting.OMLAAG;
                break;
           
            case KeyEvent.VK_UP: 
                this.richting = Richting.OMHOOG;
                break;
            
            case KeyEvent.VK_LEFT: 
                this.richting = Richting.LINKS;
                break;
            
            case KeyEvent.VK_RIGHT: 
                this.richting = Richting.RECHTS;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!this.arrowKeyPressed) {
            this.setRichting(e);
            this.probeerTeBewegen(this.richting);
        }
        this.arrowKeyPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.arrowKeyPressed = false;
    }

}

