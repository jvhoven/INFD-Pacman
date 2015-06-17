/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spel.spelelementen;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import spel.levelelementen.LeegVakje;
import spel.levelelementen.Positie;

/**
 *
 * @author Jeffrey
 */
public class PoppetjeTest {
    
    private Poppetje instance = null;
    private LeegVakje startPositie = null;
    
    public PoppetjeTest() {
        startPositie = new LeegVakje(new Positie(1,1));
        instance = new Pacman(startPositie);
        instance.setHuidigVakje(startPositie);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of reset method, of class Poppetje.
     */
    @Test
    public void testReset() {
        
        Positie expResult = instance.huidigVakje.positie;
        
        LeegVakje nieuwVakje = new LeegVakje(new Positie(12, 4));
        instance.beweegNaar(nieuwVakje);
        instance.reset();
        
        Positie result = instance.huidigVakje.positie;
        
        assertEquals("positie zou gereset moeten zijn", expResult, result);
    }

    /**
     * Test of beweegNaar method, of class Poppetje.
     */
    @Test
    public void testBeweegNaar() {
        
        LeegVakje volgendVakje = new LeegVakje(new Positie(2, 1));
        instance.beweegNaar(volgendVakje);
        
        Positie expResult = volgendVakje.positie;
        Positie result = instance.huidigVakje.positie;
        
        assertEquals("failure", expResult, result);
    }
}
