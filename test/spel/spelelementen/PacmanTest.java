/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spel.spelelementen;

import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import spel.enums.Richting;
import spel.interfaces.Eetbaar;
import spel.levelelementen.LeegVakje;
import spel.levelelementen.Positie;

/**
 *
 * @author Jeffrey
 */
public class PacmanTest {
    
    private Pacman instance = null;
    private LeegVakje startVakje = null;
    
    public PacmanTest() {
        startVakje = new LeegVakje(new Positie(1, 1));
        instance = new Pacman(startVakje);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getLevens method, of class Pacman.
     */
    @Test
    public void testGetLevens() {
        int expResult = 3;
        int result = instance.getLevens();
        assertEquals(expResult, result);
    }

    /**
     * Test of verwijderLeven method, of class Pacman.
     */
    @Test
    public void testVerwijderLeven() {
        int expResult = 2;
        instance.verwijderLeven();
        int result = instance.getLevens();
        assertEquals(expResult, result);
    }

    /**
     * Test of etenEetbaarSpelElement method, of class Pacman.
     */
    @Test
    public void testEtenEetbaarSpelElement() {
        LinkedHashMap<Eetbaar, Integer> tests = new LinkedHashMap<>();
        
        // Test cases opstellen
        tests.put(new Kers(startVakje), 100);
        tests.put(new Bolletje(startVakje), 10);
        tests.put(new Superbolletje(startVakje), 10);
        tests.put(new Spookje(startVakje), 200);
        
        for(Entry<Eetbaar, Integer> entry : tests.entrySet()) {
            int expResult = entry.getValue();
            int result = entry.getKey().getPunten();
            assertEquals("failure", result, expResult);
        }
    
    }

    /**
     * Test of getRichting method, of class Pacman.
     */
    @Test
    public void testGetRichting() {
        Map<Richting, Integer> tests = new LinkedHashMap<>();
        
        // Tests aanmaken
        tests.put(Richting.OMHOOG, KeyEvent.VK_UP);
        tests.put(Richting.OMLAAG, KeyEvent.VK_DOWN);
        tests.put(Richting.RECHTS, KeyEvent.VK_RIGHT);
        tests.put(Richting.LINKS, KeyEvent.VK_LEFT);
       
        for(Entry<Richting, Integer> test : tests.entrySet()) {
            
            Richting expResult = test.getKey();
            Richting result = instance.getRichting(test.getValue());
            assertEquals("failure", result, expResult);
        }
    }
}
