package spel.AIs;

import java.util.ArrayList;
import java.util.Random;
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
        ArrayList<LeegVakje> legeBuurVakjes = this.spookje.getHuidigVakje().getLegeBuurVakjes();
        if (this.vorigVakje != null) {
            legeBuurVakjes.remove(this.vorigVakje);
        }
        this.volgendVakje = legeBuurVakjes.get(RandomNumberGenerator.getRandomInt(legeBuurVakjes.size()));
        this.vorigVakje = this.spookje.getHuidigVakje();
    }
}
