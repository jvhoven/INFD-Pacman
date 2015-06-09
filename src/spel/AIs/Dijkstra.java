/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spel.AIs;

import java.util.ArrayList;
import java.util.HashMap;
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
    private ArrayList<LeegVakje> Q;

    //datastructuur waarin de knopen worden opgeslagen waarvan de kortste afstand tot de startknoop definitief bepaald is.
    private ArrayList<LeegVakje> S;

    //datastructuur waarin per knoop wordt bijgehouden wat zijn voorganger is in het kortste pad.
    private HashMap<LeegVakje, LeegVakje> P;

    private int huidigeAfstand = 0;

    public Dijkstra(Spookje spookje, Pacman pacman) {
        super(spookje, pacman);

        M = new HashMap<>();
        Q = new ArrayList<>();
        S = new ArrayList<>();
        P = new HashMap<>();

    }

    @Override
    public void berekenVolgendVakje() {
        if (pacman.getHuidigVakje() != this.pacmanLocatie) {
            pacmanLocatie = pacman.getHuidigVakje();

            M.put(pacmanLocatie, huidigeAfstand);
            Q.add(pacmanLocatie);

            while (!Q.isEmpty()) {
                //for(LeegVakje vakje :  )
            }
        }
    }

    public void relaxeerBuren(LeegVakje vakje) {
        ArrayList<LeegVakje> buurVakjes = vakje.getLegeBuurVakjes();
        huidigeAfstand++;
        for (LeegVakje buurVakje : buurVakjes) {
            if (!S.contains(buurVakje)) {
                if (M.containsKey(buurVakje)) {
                    int afstandUitM = M.get(buurVakje);
                    if (huidigeAfstand < afstandUitM) {
                        M.replace(buurVakje, huidigeAfstand);
                    }
                } else {
                    M.put(vakje, huidigeAfstand);
                }
                P.put(buurVakje, vakje);
                Q.add(buurVakje);
                Q.remove(vakje);
                S.add(vakje);
            }
        }

    }
}
