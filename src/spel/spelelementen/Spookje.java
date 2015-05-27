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
import java.net.URL;
import javax.imageio.ImageIO;
import spel.levelelementen.LeegVakje;
import spel.levelelementen.Vakje;

/**
 *
 * @author Hans
 */
public class Spookje extends Poppetje {

    private BufferedImage spookPlaatje = null;

    public Spookje(Vakje startVakje) {
        setHuidigVakje((LeegVakje) startVakje);

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL urlPath = classLoader.getResource("images/spook.png");
            if (urlPath != null) {
                File file = new File(urlPath.getFile());
                spookPlaatje = ImageIO.read(file);
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
            if (spookPlaatje == null) {
                g.setColor(Color.blue);
                g.fillOval(x, y, Vakje.SIZE - 10, Vakje.SIZE - 10);
            } else {
                g.drawImage(spookPlaatje, x, y, null);
            }
        }
    }

}
