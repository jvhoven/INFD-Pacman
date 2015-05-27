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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
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

    public Pacman(LeegVakje startVakje) {
        this.huidigVakje = startVakje;

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL urlPath = classLoader.getResource("images/pacman.png");
            if (urlPath != null) {
                File file = new File(urlPath.getFile());
                pacmanPlaatje = ImageIO.read(file);
            }
        } catch (IOException e) {
            System.err.print(e);
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
                g.drawImage(pacmanPlaatje, x, y, null);
            }
        }
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
                nieuwePositie = new Positie(huidigePositie.x, huidigePositie.y + 1);
                break;
            case KeyEvent.VK_UP:
                nieuwePositie = new Positie(huidigePositie.x, huidigePositie.y - 1);
                break;
            case KeyEvent.VK_LEFT:
                nieuwePositie = new Positie(huidigePositie.x - 1, huidigePositie.y);
                break;
            case KeyEvent.VK_RIGHT:
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
