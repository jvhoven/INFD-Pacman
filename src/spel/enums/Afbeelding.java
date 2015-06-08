/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spel.enums;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;

/**
 *
 * @author Jeffrey
 */
public enum Afbeelding {
    
     
    SPOOK_BLAUW("spook_een.png"), SPOOK_ROZE("spook_drie.png"), SPOOK_ROOD("spook_twee.png"), PACMAN("pacman.png");
    private final String afbeelding;
    
    private Afbeelding(String afbeelding) {
        this.afbeelding = afbeelding;
    }
    
    public BufferedImage getAfbeelding() {
        
        try {
            ClassLoader classLoader = getClass().getClassLoader();            
            URI uriPath = classLoader.getResource("images/" + afbeelding).toURI();
            System.out.println(uriPath);
            if (uriPath != null) {
                File file = new File(uriPath);
                return ImageIO.read(file);
            }
        } catch (Exception e) {
            System.err.print("Kon afbeelding " + afbeelding + " niet vinden.");
        }
        
        return null;
    }
    
}
