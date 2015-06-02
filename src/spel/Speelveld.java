package spel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import spel.levelelementen.*;
import spel.spelelementen.*;

/**
 *
 * @author Jeffrey
 */
public class Speelveld extends JPanel {

    private SpelStatus spelStatus = null;
    private Vakje[][] level = null;
    private ArrayList<Spookje> spookjes = new ArrayList<>();
    private Pacman pacman = null;

    public Speelveld() {
        super();

        setPreferredSize(new Dimension(500, 500));
        setFocusable(true);
        requestFocus();

        initialiseer();
    }

    private void initialiseer() {
        int breedte = 5;
        int hoogte = 5;

        int[][] levelInfo = getLevelInfo();

        initLevel(levelInfo);
        setBuurvakjesLevel();

        this.repaint();

        this.spelStatus = SpelStatus.GEPAUZEERD;
    }
    
    public int[][] getLevelInfo(){
        int[][] levelInfo = {
            {1, 1, 1, 2, 1},
            {0, 3, 0, 0, 1},
            {0, 1, 3, 1, 1},
            {0, 3, 0, 3, 1},
            {1, 1, 1, 1, 1}
        };
        
        return levelInfo;
    }

    public void initLevel(int[][] levelInfo) {
        level = new Vakje[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Positie nieuwePositie = new Positie(i + 1, j + 1);
                Vakje nieuwVakje = null;

                switch (levelInfo[i][j]) {
                    case 0:
                        nieuwVakje = new LeegVakje(nieuwePositie);
                        break;
                    case 1:
                        nieuwVakje = new Muur(nieuwePositie);
                        break;
                    case 2:
                        nieuwVakje = new LeegVakje(nieuwePositie);
                        this.pacman = new Pacman(this, (LeegVakje)nieuwVakje);
                        ((LeegVakje) nieuwVakje).toevoegenPoppetje(pacman);
                        break;
                    case 3:
                        nieuwVakje = new LeegVakje(nieuwePositie);
                        Spookje spookje = new Spookje(this, (LeegVakje)nieuwVakje);
                        ((LeegVakje) nieuwVakje).toevoegenPoppetje(spookje);

                        // Voeg toe aan de array
                        spookjes.add(spookje);
                        break;

                }

                level[i][j] = nieuwVakje;
            }
        }
    }

    public void setBuurvakjesLevel() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                level[i][j].setBuurVakjes(level);
            }
        }
    }

    private void teken(Graphics2D g) {

        this.setBackground(new Color(224, 224, 224));

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                level[i][j].teken(g);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.teken((Graphics2D) g);
    }

    public void start() {
        this.spelStatus = SpelStatus.GESTART;
        this.addKeyListener(pacman);
    }

    public void pauzeer() {
        this.spelStatus = SpelStatus.GEPAUZEERD;
        this.removeKeyListener(pacman);
    }

    public void reset() {
        this.pauzeer();
        this.initialiseer();
    }

    public SpelStatus getSpelStatus() {
        return this.spelStatus;
    }
}
