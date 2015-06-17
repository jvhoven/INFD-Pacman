/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spel.AIs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import spel.levelelementen.LeegVakje;
import spel.spelelementen.Pacman;
import spel.spelelementen.Spookje;

/**
 *
 * @author Jeffrey
 */
public class AStar extends SmartAI {

    PriorityQueue<LeegVakje> open = null;
    ArrayList<LeegVakje> closed = null;

    public AStar(Spookje spookje, Pacman pacman) {
        super(spookje, pacman);
        open = new PriorityQueue<LeegVakje>();
        closed = new ArrayList<>();
    }

    @Override
    public void berekenVolgendVakje() {
        targetVakje = spookje.getHuidigVakje();

        if (pacman.getHuidigVakje() != this.startVakje) {
            open.clear();
            closed.clear();
            startVakje = pacman.getHuidigVakje();

            open.add(startVakje);

            while (!open.isEmpty() && !closed.contains(targetVakje)) {
                LeegVakje huidigVakje = open.poll();
                closed.add(huidigVakje);

                analyseerBuren(huidigVakje);
            }
        }
        
        for(LeegVakje vakje : closed){
            System.out.println(vakje + " G: " + vakje.G + " H: " + vakje.H + " F: " + vakje.F);
        }
        
        if (!this.spookje.getIsEetbaar()) {
            this.volgendVakje = targetVakje.voorliggendVakje;
        } else {
            setVolgendVakjeEetbaarSpookje();
        }
    }

    public void setVolgendVakjeEetbaarSpookje() {
        LeegVakje vakjeRichtingPacman = targetVakje.voorliggendVakje;
        ArrayList<LeegVakje> buurVakjes = targetVakje.getLegeBuurVakjes();
        LeegVakje vorigVakje = spookje.getVorigVakje();
        
        Iterator iterator = buurVakjes.iterator();
        while (iterator.hasNext()) {
            LeegVakje buurVakje = (LeegVakje) iterator.next();
            if (buurVakje != vakjeRichtingPacman && buurVakje.getSpookje() == null && vorigVakje != buurVakje) {
                volgendVakje = buurVakje;
            }
        }

        spookje.setVorigVakje(spookje.getHuidigVakje());
    }

    public void analyseerBuren(LeegVakje huidigVakje) {
        ArrayList<LeegVakje> buurVakjes = huidigVakje.getLegeBuurVakjes();
        Iterator iterator = buurVakjes.iterator();

        while (iterator.hasNext()) {
            LeegVakje buurVakje = (LeegVakje) iterator.next();
            if (!open.contains(buurVakje) && !closed.contains(buurVakje)) {

                buurVakje.H = berekenHeuristischeWaarde(buurVakje);

                buurVakje.voorliggendVakje = huidigVakje;
                buurVakje.G = 1 + buurVakje.voorliggendVakje.G;
                buurVakje.F = buurVakje.H + buurVakje.G;

                open.add(buurVakje);
            }
        }

    }

    public int berekenHeuristischeWaarde(LeegVakje huidigVakje) {
        int dx = Math.abs(huidigVakje.positie.x - targetVakje.positie.x);
        int dy = Math.abs(huidigVakje.positie.y - targetVakje.positie.y);

        int heuristischeWaarde = dx + dy;
        heuristischeWaarde *= 10;

        return heuristischeWaarde;
    }

}
