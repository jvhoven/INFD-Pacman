package spel;

import spel.enums.SpelStatus;
import spel.enums.VakjeType;
import spel.spelelementen.Bolletje;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JPanel;
import spel.enums.Afbeelding;
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
     
    
    
    // Meerdere afbeeldingen voor spookjes
    Queue<Afbeelding> spookjes = new LinkedList<>(Arrays.asList(Afbeelding.SPOOK_BLAUW, Afbeelding.SPOOK_ROOD, Afbeelding.SPOOK_ROZE));
    
    public Speelveld(Spel spel) {
        super();

        this.spel = spel;
        
        setPreferredSize(new Dimension(500, 500));
        setFocusable(true);
        requestFocus();

        initialiseer();
    }

    private void initialiseer() {

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
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 2, 3, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 4, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 4, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
        };

        return levelInfo;
    }

    public void initLevel(int[][] levelInfo) {
        level = new Vakje[15][15];

        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 15; y++) {
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
                        this.pacman = new Pacman(this, (LeegVakje) nieuwVakje);
                        ((LeegVakje) nieuwVakje).toevoegenInhoud(pacman);
                        break;
                    case VakjeType.SPOOKJE:
                        nieuwVakje = new LeegVakje(nieuwePositie);
                        ((LeegVakje) nieuwVakje).toevoegenInhoud(new Bolletje((LeegVakje) nieuwVakje));
                        ((LeegVakje) nieuwVakje).toevoegenInhoud(new Spookje(this, (LeegVakje) nieuwVakje, spookjes.poll()));
                        break;
                    case VakjeType.BOLLETJE:
                        nieuwVakje = new LeegVakje(nieuwePositie);
                        ((LeegVakje) nieuwVakje).toevoegenInhoud(new Bolletje((LeegVakje) nieuwVakje));
                        break;
                }

                level[x][y] = nieuwVakje;
            }
        }
    }

    public void setBuurvakjesLevel() {
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 15; y++) {
                level[x][y].setBuurVakjes(level);
            }
        }
    }

    private void teken(Graphics2D g) {

        this.setBackground(new Color(224, 224, 224));

        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 15; y++) {
                level[x][y].teken(g);
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
