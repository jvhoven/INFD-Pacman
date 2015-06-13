package spel.AIs;

import java.util.ArrayList;
import spel.levelelementen.LeegVakje;
import spel.spelelementen.Spookje;
import utilities.RandomNumberGenerator;

public class RandomAI extends AI {

    private LeegVakje vorigVakje = null;

    public RandomAI(Spookje spookje) {
        super(spookje);
    }

    @Override
    public void berekenVolgendVakje() {
        ArrayList<LeegVakje> legeBuurVakjes = this.spookje.getHuidigVakje().getLegeBuurVakjesZonderSpookje(null);
        if (this.vorigVakje != null) {
            legeBuurVakjes.remove(this.vorigVakje);
        }

        if (legeBuurVakjes.size() > 0) {
            this.volgendVakje = legeBuurVakjes.get(RandomNumberGenerator.getRandomInt(legeBuurVakjes.size()));

        }
        this.vorigVakje = this.spookje.getHuidigVakje();
    }
}
