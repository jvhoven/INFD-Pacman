package spel.spelelementen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import spel.Richting;
import spel.Speelveld;
import spel.interfaces.Eetbaar;
import spel.levelelementen.LeegVakje;
import spel.levelelementen.Vakje;

/**
 *
 * @author Hans
 */
public class Pacman extends Poppetje implements KeyListener {

    private boolean arrowKeyPressed = false;
    private BufferedImage pacmanPlaatje = null;
    private Richting richting;

    public Pacman(Speelveld speelveld, LeegVakje startVakje) {
        this.speelveld = speelveld;
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
                        
            int x = huidigVakje.positie.x * Vakje.SIZE - (Vakje.SIZE + 10);
            int y = huidigVakje.positie.y * Vakje.SIZE - (Vakje.SIZE - 12);
            if (pacmanPlaatje == null) {
                g.setColor(Color.yellow);
                g.fillOval(x, y, Vakje.SIZE - 10, Vakje.SIZE - 10);
            } else {

                BufferedImage afbeelding = setOrientatie();
                g.drawImage(afbeelding, x + (Vakje.SIZE / 2), y + (Vakje.SIZE / 2), Vakje.SIZE - 10, Vakje.SIZE - 10, null);
            }
        }
    }

    private BufferedImage setOrientatie() {

        int width = pacmanPlaatje.getWidth() / 2;
        int height = pacmanPlaatje.getHeight() / 2;

        BufferedImage pacmanOrientatie = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D bg = pacmanOrientatie.createGraphics();

        bg.rotate(richting.getGraden(), width / 2, height / 2);
        bg.drawImage(pacmanPlaatje, 0, 0, width, height, null);
        bg.dispose();

        return pacmanOrientatie;
    }

    private void probeerTeBewegen(Richting richting) {
        Vakje buurVakje = this.huidigVakje.getBuurVakje(richting);

        if (buurVakje != null && buurVakje instanceof LeegVakje) {
            LeegVakje target = (LeegVakje) buurVakje;
            this.beweegNaar(target);
            verwerkInhoudHuidigVakje();
        }
    }

    public void verwerkInhoudHuidigVakje() {
        // Als het een eetbaar object is
        ArrayList<Eetbaar> eetbareInhoud = huidigVakje.getEetbareInhoud();

        for (Eetbaar e : eetbareInhoud) {
            if (e instanceof Spookje) {
                System.out.println("Ik eet een spookje");
            }

            int punten = e.opeten();
            speelveld.addPunten(punten);
        }
    }

    private void setRichting(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                richting = Richting.OMLAAG;
                break;
            case KeyEvent.VK_UP:
                richting = Richting.OMHOOG;
                break;
            case KeyEvent.VK_LEFT:
                richting = Richting.LINKS;
                break;
            case KeyEvent.VK_RIGHT:
                richting = Richting.RECHTS;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!arrowKeyPressed) {
            this.setRichting(e);
            this.probeerTeBewegen(richting);
        }
        arrowKeyPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        arrowKeyPressed = false;
    }
}
