/*
 * Decompiled with CFR 0_101.
 */
package spel.enums;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintStream;
import java.net.URI;
import java.net.URL;
import javax.imageio.ImageIO;

public enum Afbeelding {
    SPOOK_BLAUW("spook_een.png"),
    SPOOK_ROZE("spook_drie.png"),
    SPOOK_ROOD("spook_twee.png"),
    PACMAN("pacman.png");
    
    private final String afbeelding;

    private Afbeelding(String afbeelding) {
        this.afbeelding = afbeelding;
    }

    public BufferedImage getAfbeelding() {
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            URI uriPath = classLoader.getResource("images/" + this.afbeelding).toURI();
            System.out.println(uriPath);
            if (uriPath != null) {
                File file = new File(uriPath);
                return ImageIO.read(file);
            }
        }
        catch (Exception e) {
            System.err.print("Kon afbeelding " + this.afbeelding + " niet vinden.");
        }
        return null;
    }
}

