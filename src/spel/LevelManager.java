package spel;

import java.util.ArrayList;
import java.util.Random;
import spel.enums.VakjeType;
import spel.interfaces.Eetbaar;
import spel.interfaces.SpelElement;
import spel.levelelementen.LeegVakje;
import spel.levelelementen.Muur;
import spel.levelelementen.Positie;
import spel.levelelementen.Vakje;
import spel.spelelementen.*;
import utilities.RandomNumberGenerator;

;

public class LevelManager {

    public final int LEVEL_SIZE = 15;
    private int huidigLevelNummer = 1;
    private int totaalAantalBolletjes = 0;

    public Vakje[][] getVolgendLevel(Pacman pacman, ArrayList<Spookje> spookjes) {
        ++this.huidigLevelNummer;
        return this.getLevel(pacman, spookjes);
    }

    public Vakje[][] getLevel(Pacman pacman, ArrayList<Spookje> spookjes) {
        int[][] levelInfo = this.getLevelInfo();
        Vakje[][] level = this.initLevel(levelInfo, pacman, spookjes);
        this.setBuurvakjesLevel(level);
        this.spawnSuperBolletjes(level);
        return level;
    }

    public int[][] getLevelInfo() {
        int[][] levelInfo = null;
        if (this.huidigLevelNummer == 1) {
            int[][] tempLevelInfo;

            levelInfo = tempLevelInfo = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 3, 3, 1},
                {1, 4, 1, 1, 1, 1, 4, 4, 4, 4, 4, 1, 4, 3, 1},
                {1, 4, 1, 4, 4, 1, 4, 4, 4, 4, 4, 1, 4, 4, 1},
                {1, 4, 1, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 1},
                {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
                {1, 4, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 1},
                {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
                {1, 4, 1, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
                {1, 4, 1, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
                {1, 4, 1, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
                {1, 4, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
                {1, 4, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
                {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };
        } else if (this.huidigLevelNummer == 2 || this.huidigLevelNummer == 3) {
            // empty if block
        }
        return levelInfo;
    }

    public Vakje[][] initLevel(int[][] levelInfo, Pacman pacman, ArrayList<Spookje> spookjes) {
        Vakje[][] level = new Vakje[LEVEL_SIZE][LEVEL_SIZE];
        int spookjesIndex = spookjes.size();
        this.totaalAantalBolletjes = 0;
        for (int x = 0; x < LEVEL_SIZE; ++x) {
            for (int y = 0; y < LEVEL_SIZE; ++y) {
                Positie nieuwePositie = new Positie(x + 1, y + 1);
                Vakje nieuwVakje = null;
                switch (levelInfo[y][x]) {
                    case VakjeType.LEEG_VAKJE:
                        nieuwVakje = new LeegVakje(nieuwePositie);
                        break;

                    case VakjeType.MUUR:
                        nieuwVakje = new Muur(nieuwePositie);
                        break;

                    case VakjeType.PACMAN:
                        nieuwVakje = new LeegVakje(nieuwePositie);
                        ((LeegVakje) nieuwVakje).toevoegenSpelElement(pacman);
                        pacman.setStartVakje((LeegVakje) nieuwVakje);
                        break;

                    case VakjeType.SPOOKJE:
                        nieuwVakje = new LeegVakje(nieuwePositie);
                        ((LeegVakje) nieuwVakje).toevoegenSpelElement(spookjes.get(spookjesIndex - 1));

                        spookjes.get(spookjesIndex - 1).setStartVakje((LeegVakje) nieuwVakje);
                        --spookjesIndex;

                        break;

                    case VakjeType.BOLLETJE:
                        nieuwVakje = new LeegVakje(nieuwePositie);
                        ((LeegVakje) nieuwVakje).toevoegenSpelElement(new Bolletje((LeegVakje) nieuwVakje));
                        this.totaalAantalBolletjes++;
                        break;

                    case VakjeType.KERS:
                        nieuwVakje = new LeegVakje(nieuwePositie);
                        ((LeegVakje) nieuwVakje).toevoegenSpelElement(new Kers((LeegVakje) nieuwVakje));
                        break;
                }
                level[x][y] = nieuwVakje;
            }
        }
        return level;
    }

    public void setBuurvakjesLevel(Vakje[][] level) {
        for (int x = 0; x < LEVEL_SIZE; ++x) {
            for (int y = 0; y < LEVEL_SIZE; ++y) {
                level[x][y].setBuurVakjes(level);
            }
        }
    }

    private void spawnSuperBolletjes(Vakje[][] level) {
        double percentage = (15 - this.huidigLevelNummer * 3 - 3);
        percentage /= 100;
        int aantalSuperBolletjes = (int) ((double) this.totaalAantalBolletjes * percentage);

        while (aantalSuperBolletjes > 0) {
            int x = RandomNumberGenerator.getRandomInt(LEVEL_SIZE);
            int y = RandomNumberGenerator.getRandomInt(LEVEL_SIZE);

            Vakje vakje = level[x][y];

            if (vakje instanceof LeegVakje) {
                LeegVakje leegvakje = (LeegVakje) vakje;
                Bolletje b = leegvakje.getBolletje();
                if (b != null && b instanceof SpelElement) {
                    leegvakje.verwijderSpelElement((SpelElement) b);
                    leegvakje.toevoegenSpelElement(new Superbolletje(leegvakje));
                    aantalSuperBolletjes--;
                }
            }
        }
    }

    public int getTotaalAantalBolletjes() {
        return this.totaalAantalBolletjes;
    }
}
