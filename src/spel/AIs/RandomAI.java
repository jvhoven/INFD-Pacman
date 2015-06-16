package spel.AIs;

import java.util.ArrayList;
import spel.levelelementen.LeegVakje;
import spel.spelelementen.Spookje;
import utilities.RandomNumberGenerator;

public class RandomAI extends AI {

    public RandomAI(Spookje spookje) {
        super(spookje);
    }

    @Override
    public void berekenVolgendVakje() {
        ArrayList<LeegVakje> legeBuurVakjes = this.spookje.getHuidigVakje().getLegeBuurVakjesZonderSpookje(null);

        LeegVakje vorigVakje = spookje.getVorigVakje();
        if (vorigVakje != null) {
            legeBuurVakjes.remove(vorigVakje);
        }

        if (legeBuurVakjes.size() > 0) {
            this.volgendVakje = legeBuurVakjes.get(RandomNumberGenerator.getRandomInt(legeBuurVakjes.size()));

        }
        spookje.setVorigVakje(spookje.getHuidigVakje());
    }
}
