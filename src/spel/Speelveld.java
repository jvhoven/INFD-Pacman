package spel;

import spel.enums.SpelStatus;
import spel.enums.ElementType;
import spel.spelelementen.Bolletje;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import spel.levelelementen.*;
import spel.spelelementen.*;

/**
 *
 * @author Jeffrey
 */
public class Speelveld extends JPanel {

    private Spel spel = null;
    private SpelStatus spelStatus = null;
    private Vakje[][] level = null;
    private Pacman pacman = null;
    private int huidigeScore = 0;

    public Speelveld(Spel spel) {
        super();

        this.spel = spel;
        
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
        this.huidigeScore = 0;
        this.spel.showScore(huidigeScore);
    }

    public int[][] getLevelInfo() {
        int[][] levelInfo = {
            {1, 1, 1, 2, 1},
            {4, 3, 4, 4, 1},
            {4, 1, 3, 1, 1},
            {4, 3, 4, 3, 1},
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
                    case ElementType.LEEG_VAKJE:
                        nieuwVakje = new LeegVakje(nieuwePositie);
                        break;
                    case ElementType.MUUR:
                        nieuwVakje = new Muur(nieuwePositie);
                        break;
                    case ElementType.PACMAN:
                        nieuwVakje = new LeegVakje(nieuwePositie);
                        this.pacman = new Pacman(this, (LeegVakje) nieuwVakje);
                        ((LeegVakje) nieuwVakje).toevoegenInhoud(pacman);
                        break;
                    case ElementType.SPOOKJE:
                        nieuwVakje = new LeegVakje(nieuwePositie);
                        ((LeegVakje) nieuwVakje).toevoegenInhoud(new Bolletje((LeegVakje) nieuwVakje));
                        ((LeegVakje) nieuwVakje).toevoegenInhoud(new Spookje(this, (LeegVakje) nieuwVakje));
                        break;
                    case ElementType.BOLLETJE:
                        nieuwVakje = new LeegVakje(nieuwePositie);
                        ((LeegVakje) nieuwVakje).toevoegenInhoud(new Bolletje((LeegVakje) nieuwVakje));
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
    
    public void addPunten(int punten){
        this.huidigeScore += punten;
        
        spel.showScore(huidigeScore);
    }
}
