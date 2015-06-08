/*
 * Decompiled with CFR 0_101.
 */
package AIs;

import AIs.AI;
import java.util.ArrayList;
import java.util.Random;
import spel.levelelementen.LeegVakje;
import spel.spelelementen.Spookje;

public class RandomAI
extends AI {
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
        this.volgendVakje = legeBuurVakjes.get(this.getRandomValue(legeBuurVakjes.size()));
        this.vorigVakje = this.spookje.getHuidigVakje();
    }

    private int getRandomValue(int maxValue) {
        Random random = new Random();
        return random.nextInt(maxValue);
    }
}

