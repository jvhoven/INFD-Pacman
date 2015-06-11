/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spel.AIs;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;
import spel.levelelementen.LeegVakje;
import spel.spelelementen.Pacman;
import spel.spelelementen.Spookje;

/**
 *
 * @author Jeffrey
 */
public class AStar extends SmartAI {
    
    boolean targetVakjeBereikt = false;
    Queue<LeegVakje> open = null;
    LinkedHashMap<LeegVakje, Double> closed = null;
    
    public AStar(Spookje spookje, Pacman pacman) {
        super(spookje, pacman);
        open = new LinkedList<>();
        closed = new LinkedHashMap<>();
    }
    
    @Override
    public void berekenVolgendVakje() {
        startVakje = spookje.getHuidigVakje();

        if (pacman.getHuidigVakje() != this.targetVakje) {
            
            open.clear();
            closed.clear();
            targetVakjeBereikt = false;
            
            targetVakje = pacman.getHuidigVakje();
            open.add(startVakje);
            
            while (!open.isEmpty()) {
                LeegVakje current = open.poll();
                System.out.println(current);
                System.out.println(open);
                analyseerBuren(current);
                closed.put(current, berekenHeuristischeWaarde(current, targetVakje));
               
                if(targetVakjeBereikt) {
                    break;
                }
            }
        }
        
        System.out.println(closed);
        //setVolgendVakje();
    }
    
    public void analyseerBuren(LeegVakje huidig) {
        
        ArrayList<LeegVakje> buren = huidig.getLegeBuurVakjes();
        LeegVakje kandidaat = buren.get(0);
     
        for(LeegVakje buur : buren) { 
            open.add(buur);
        }
        
        if(kandidaat == targetVakje) {
            targetVakjeBereikt = true;
        }
        
        if(!closed.containsKey(kandidaat)) {
            open.add(kandidaat);
        }
    }
    
    public double berekenHeuristischeWaarde(LeegVakje huidig, LeegVakje target) {
        double dx = Math.abs(huidig.positie.x - target.positie.x);
        double dy = Math.abs(huidig.positie.y - target.positie.y);
        
        double heuristischeWaarde = dx+dy;
        return heuristischeWaarde;
    }
    
}
