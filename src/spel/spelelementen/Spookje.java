/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spel.spelelementen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import spel.Speelveld;
import spel.levelelementen.LeegVakje;
import spel.levelelementen.Vakje;

/**
 *
 * @author Hans
 */
public class Spookje extends Poppetje {

    private BufferedImage spookPlaatje = null;

    public Spookje(Speelveld speelveld, LeegVakje startVakje){
        this.speelveld = speelveld;
        setHuidigVakje(startVakje);

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URI uriPath = classLoader.getResource("images/spook.png").toURI();
            if (uriPath != null) {
                File file = new File(uriPath);
                spookPlaatje = ImageIO.read(file);
            }
        } catch (IOException e) {
            System.err.print(e);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Spookje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void teken(Graphics2D g) {

        if (huidigVakje != null) {
            int x = huidigVakje.positie.x * Vakje.SIZE - Vakje.SIZE + 5;
            int y = huidigVakje.positie.y * Vakje.SIZE - Vakje.SIZE + 5;
            if (spookPlaatje == null) {
                g.setColor(Color.blue);
                g.fillOval(x, y, Vakje.SIZE - 10, Vakje.SIZE - 10);
            } else {                
                
                int width = spookPlaatje.getWidth();
                int height = spookPlaatje.getHeight();
                
                g.drawImage(spookPlaatje, x + (width / 4) - 5, y + (height / 4), width / 2, height / 2, null);
            }
        }
    }

}
