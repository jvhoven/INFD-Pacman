/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spel.spelelementen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import spel.Richting;
import spel.levelelementen.LeegVakje;
import spel.levelelementen.Positie;
import spel.levelelementen.Vakje;

/**
 *
 * @author Hans
 */
public class Pacman extends Poppetje implements KeyListener {

    private boolean arrowKeyPressed = false;
    private BufferedImage pacmanPlaatje = null;
    private Richting richting;

    public Pacman(LeegVakje startVakje) {
        this.huidigVakje = startVakje;
        this.richting = Richting.NEUTRAAL;
        
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URI uriPath = classLoader.getResource("images/pacman.png").toURI();
            if (uriPath != null) {
                File file = new File(uriPath);
                pacmanPlaatje = ImageIO.read(file);
                
            }
        } catch (IOException e) {
            System.err.print(e);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Pacman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void teken(Graphics2D g) {

        if (huidigVakje != null) {
            int x = huidigVakje.positie.x * Vakje.SIZE - Vakje.SIZE + 5;
            int y = huidigVakje.positie.y * Vakje.SIZE - Vakje.SIZE + 5;
            if (pacmanPlaatje == null) {
                g.setColor(Color.yellow);
                g.fillOval(x, y, Vakje.SIZE - 10, Vakje.SIZE - 10);
            } else {
                
                BufferedImage afbeelding = setOrientatie();
                g.drawImage(afbeelding, x + (pacmanPlaatje.getWidth() / 4) - 5, y + (pacmanPlaatje.getHeight() / 4), null);
            }
        }
    }
    
    private BufferedImage setOrientatie() {     
        
        int width = pacmanPlaatje.getWidth() / 2;
        int height = pacmanPlaatje.getHeight() / 2;
        
        BufferedImage pacmanOrientatie = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D bg = pacmanOrientatie.createGraphics();               
        
        bg.rotate(richting.getGraden(), width/2, height/2);
        bg.drawImage(pacmanPlaatje, 0, 0, width, height, null);
        bg.dispose();
        
        return pacmanOrientatie;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    private void controleerBuurvakje(Positie nieuwePositie) {
        Vakje buurVakje = this.huidigVakje.getBuurVakje(nieuwePositie);

        if (buurVakje != null && buurVakje instanceof LeegVakje) {
            this.beweegNaar((LeegVakje) buurVakje);
        }
    }

    private Positie getNieuwePositie(KeyEvent e) {

        Positie nieuwePositie = null;
        Positie huidigePositie = this.huidigVakje.positie;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                richting = Richting.OMLAAG;
                nieuwePositie = new Positie(huidigePositie.x, huidigePositie.y + 1);
                break;
            case KeyEvent.VK_UP:
                richting = Richting.OMHOOG;
                nieuwePositie = new Positie(huidigePositie.x, huidigePositie.y - 1);
                break;
            case KeyEvent.VK_LEFT:
                richting = Richting.LINKS;
                nieuwePositie = new Positie(huidigePositie.x - 1, huidigePositie.y);
                break;
            case KeyEvent.VK_RIGHT:
                richting = Richting.RECHTS;
                nieuwePositie = new Positie(huidigePositie.x + 1, huidigePositie.y);
                break;
        }

        return nieuwePositie;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!arrowKeyPressed) {
            Positie buurPositie = getNieuwePositie(e);

            if (buurPositie != null) {
                this.controleerBuurvakje(buurPositie);
            }
        }
        arrowKeyPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        arrowKeyPressed = false;
    }
}
