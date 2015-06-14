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

    boolean targetVakjeBereikt = false;
    PriorityQueue<LeegVakje> open = null;
    ArrayList<LeegVakje> closed = null;

    public AStar(Spookje spookje, Pacman pacman) {
        super(spookje, pacman);
        open = new PriorityQueue<LeegVakje>();
        closed = new ArrayList<>();
    }

    @Override
    public void berekenVolgendVakje() {
        startVakje = pacman.getHuidigVakje();

        if (pacman.getHuidigVakje() != this.targetVakje) {

            open.clear();
            closed.clear();
            targetVakjeBereikt = false;

            targetVakje = spookje.getHuidigVakje();
            open.add(startVakje);

            while (!open.isEmpty() && !closed.contains(targetVakje)) {
                LeegVakje current = open.poll();
                closed.add(current);

                analyseerBuren(current);
            }
        }
        this.volgendVakje = targetVakje.parent;
    }

    public void analyseerBuren(LeegVakje huidigVakje) {
        ArrayList<LeegVakje> buurVakjes = huidigVakje.getLegeBuurVakjes();
        Iterator iterator = buurVakjes.iterator();

        while (iterator.hasNext()) {
            LeegVakje buurVakje = (LeegVakje) iterator.next();
            if (!open.contains(buurVakje) && !closed.contains(buurVakje)) {
                buurVakje.gebruiktDijkstra = false;

                buurVakje.H = berekenHeuristischeWaarde(buurVakje);

                buurVakje.parent = huidigVakje;
                buurVakje.G = 10 + buurVakje.parent.G;
                buurVakje.F = buurVakje.H + buurVakje.G;

                open.add(buurVakje);
            }
        }

    }

    public int berekenHeuristischeWaarde(LeegVakje huidig) {
        int dx = Math.abs(huidig.positie.x - targetVakje.positie.x);
        int dy = Math.abs(huidig.positie.y - targetVakje.positie.y);

        int heuristischeWaarde = dx + dy;
        return heuristischeWaarde;
    }

}
