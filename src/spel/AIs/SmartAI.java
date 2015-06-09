package spel.AIs;

import spel.levelelementen.LeegVakje;
import spel.spelelementen.Pacman;
import spel.spelelementen.Spookje;

public class SmartAI extends AI {

    protected Pacman pacman = null;
    protected LeegVakje startVakje = null;
    protected LeegVakje targetVakje = null;
    
    public SmartAI(Spookje spookje, Pacman pacman) {
        super(spookje);
        this.pacman = pacman;
    }

    @Override
    public void berekenVolgendVakje() {
    }
}
