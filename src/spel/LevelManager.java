/*
 * Decompiled with CFR 0_101.
 */
package spel;

import java.util.ArrayList;
import spel.interfaces.SpelElement;
import spel.levelelementen.LeegVakje;
import spel.levelelementen.Muur;
import spel.levelelementen.Positie;
import spel.levelelementen.Vakje;
import spel.spelelementen.Bolletje;
import spel.spelelementen.Pacman;
import spel.spelelementen.Spookje;

public class LevelManager {
    private final int LEVEL_SIZE = 15;
    int huidigLevelNummer = 1;

    public Vakje[][] resetPosities(Vakje[][] level, Pacman pacman, ArrayList<Spookje> spookjes) {
        int[][] levelInfo = this.getLevelInfo();
        int spookjesIndex = spookjes.size();
        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 15; ++j) {
                if (levelInfo[j][i] == 2) {
                    if (!(level[j][i] instanceof LeegVakje)) continue;
                    pacman.beweegNaar((LeegVakje)level[i][j]);
                    continue;
                }
                if (levelInfo[j][i] != 3 || !(level[j][i] instanceof LeegVakje)) continue;
                spookjes.get(spookjesIndex - 1).beweegNaar((LeegVakje)level[i][j]);
                --spookjesIndex;
            }
        }
        return null;
    }

    public Vakje[][] getVolgendLevel(Pacman pacman, ArrayList<Spookje> spookjes) {
        ++this.huidigLevelNummer;
        return this.getLevel(pacman, spookjes);
    }

    public Vakje[][] getLevel(Pacman pacman, ArrayList<Spookje> spookjes) {
        int[][] levelInfo = this.getLevelInfo();
        Vakje[][] level = this.initLevel(levelInfo, pacman, spookjes);
        this.setBuurvakjesLevel(level);
        return level;
    }

    public int[][] getLevelInfo() {
        int[][] levelInfo = null;
        if (this.huidigLevelNummer == 1) {
            int[][] tempLevelInfo;
            levelInfo = tempLevelInfo = new int[][]{{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 3, 3, 1}, {1, 4, 1, 1, 1, 1, 4, 4, 4, 4, 4, 1, 4, 3, 1}, {1, 4, 1, 4, 4, 1, 4, 4, 4, 4, 4, 1, 4, 4, 1}, {1, 4, 1, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 1}, {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1}, {1, 4, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 1}, {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1}, {1, 4, 1, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1}, {1, 4, 1, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1}, {1, 4, 1, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1}, {1, 4, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1}, {1, 4, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1}, {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
        } else if (this.huidigLevelNummer == 2 || this.huidigLevelNummer == 3) {
            // empty if block
        }
        return levelInfo;
    }

    public Vakje[][] initLevel(int[][] levelInfo, Pacman pacman, ArrayList<Spookje> spookjes) {
        Vakje[][] level = new Vakje[15][15];
        int spookjesIndex = spookjes.size();
        for (int x = 0; x < 15; ++x) {
            for (int y = 0; y < 15; ++y) {
                Positie nieuwePositie = new Positie(x + 1, y + 1);
                Vakje nieuwVakje = null;
                switch (levelInfo[y][x]) {
                    case 0: {
                        nieuwVakje = new LeegVakje(nieuwePositie);
                        break;
                    }
                    case 1: {
                        nieuwVakje = new Muur(nieuwePositie);
                        break;
                    }
                    case 2: {
                        nieuwVakje = new LeegVakje(nieuwePositie);
                        ((LeegVakje)nieuwVakje).toevoegenSpelElement(pacman);
                        break;
                    }
                    case 3: {
                        nieuwVakje = new LeegVakje(nieuwePositie);
                        ((LeegVakje)nieuwVakje).toevoegenSpelElement(new Bolletje((LeegVakje)nieuwVakje));
                        ((LeegVakje)nieuwVakje).toevoegenSpelElement(spookjes.get(spookjesIndex - 1));
                        --spookjesIndex;
                        break;
                    }
                    case 4: {
                        nieuwVakje = new LeegVakje(nieuwePositie);
                        ((LeegVakje)nieuwVakje).toevoegenSpelElement(new Bolletje((LeegVakje)nieuwVakje));
                    }
                }
                level[x][y] = nieuwVakje;
            }
        }
        return level;
    }

    public void setBuurvakjesLevel(Vakje[][] level) {
        for (int x = 0; x < 15; ++x) {
            for (int y = 0; y < 15; ++y) {
                level[x][y].setBuurVakjes(level);
            }
        }
    }
}

