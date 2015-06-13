/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spel.AIs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import spel.levelelementen.LeegVakje;
import spel.spelelementen.Pacman;
import spel.spelelementen.Spookje;

/**
 *
 * @author Hans
 */
public class Dijkstra extends SmartAI {

    //datastructuur voor het bijhouden van de afstanden van alle knopen tot de startknop van.
    private HashMap<LeegVakje, Integer> M;

    //datastructuur voor de knopen die al wel ‘bezocht’ zijn maar waarvan de afstand tot startknoop nog niet definitief is.
    private PriorityQueue<LeegVakje> Q;

    //datastructuur waarin de knopen worden opgeslagen waarvan de kortste afstand tot de startknoop definitief bepaald is.
    private ArrayList<LeegVakje> S;

    //datastructuur waarin per knoop wordt bijgehouden wat zijn voorganger is in het kortste pad.
    private LinkedHashMap<LeegVakje, LeegVakje> P;

    private boolean targetVakjeBereikt = false;

    public Dijkstra(Spookje spookje, Pacman pacman) {
        super(spookje, pacman);
    }

    @Override
    public void berekenVolgendVakje() {
        targetVakje = spookje.getHuidigVakje();

        if (pacman.getHuidigVakje() != this.startVakje) {
            M = new HashMap<>();
            Q = new PriorityQueue<>();
            S = new ArrayList<>();
            P = new LinkedHashMap<>();
            targetVakjeBereikt = false;

            startVakje = pacman.getHuidigVakje();

            M.put(startVakje, 0);
            Q.add(startVakje);

            while (!Q.isEmpty()) {
                LeegVakje current = Q.poll();
                S.add(current);
                relaxeerBuren(current);
            }
        }

        setVolgendVakje();
        //debug();
    }

    private void setVolgendVakje() {
        if (!P.isEmpty()) {
            if (!this.pacman.isImmuun()) {
                volgendVakje = P.get(targetVakje);
            } else {
                LeegVakje vakjeRichtingPacman = P.get(targetVakje);
                ArrayList<LeegVakje> buurVakjes = targetVakje.getLegeBuurVakjes();
                Iterator iterator = buurVakjes.iterator();

                while (iterator.hasNext()) {
                    LeegVakje buurVakje = (LeegVakje) iterator.next();
                    if (buurVakje != vakjeRichtingPacman) {
                        volgendVakje = buurVakje;
                    }
                }
            }
        }
    }

    private void relaxeerBuren(LeegVakje vakje) {
        ArrayList<LeegVakje> buurVakjes = vakje.getLegeBuurVakjes();
        
        int afstand = M.get(vakje) + 1;

        for (LeegVakje buurVakje : buurVakjes) {

            if (!S.contains(buurVakje)) {

                if (M.containsKey(buurVakje)) {
                    int afstandUitM = M.get(buurVakje);

                    if (afstand < afstandUitM) {
                        M.replace(buurVakje, afstand);
                    }
                } else {
                    M.put(buurVakje, afstand);
                }

                P.put(buurVakje, vakje);
                Q.remove(vakje);
                S.add(vakje);

                if (!targetVakjeBereikt) {
                    buurVakje.setTempAfstand(afstand);
                    Q.add(buurVakje);
                }

                if (buurVakje == targetVakje) {
                    targetVakjeBereikt = true;
                }

            }

        }
    }

    private void debug() {
        System.out.println("Positie spookje: " + spookje.getHuidigVakje());
        
        Iterator it = P.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

            LeegVakje eindVakje = (LeegVakje) pair.getKey();
            LeegVakje startVakje = (LeegVakje) pair.getValue();
            
            System.out.println(eindVakje + " - " + startVakje);
        }

        System.out.println("-----------------------------------------------");
    }
}
