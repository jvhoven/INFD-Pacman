package spel;

import spel.AIs.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import spel.enums.Afbeelding;
import spel.enums.SpelStatus;
import spel.levelelementen.LeegVakje;
import spel.levelelementen.Vakje;
import spel.spelelementen.*;
import utilities.RandomNumberGenerator;

public class Speelveld extends JPanel {

    private int huidigeScore = 0;
    
    private Spel spel = null;
    private SpelStatus spelStatus = null;
    private Vakje[][] level = null;
    private Pacman pacman = null;
    private ArrayList<Spookje> spookjes = null;
    private LevelManager levelManager = null;

    Queue<Afbeelding> spookjesAfbeeldingen = new LinkedList<>(
            Arrays.asList(
                    new Afbeelding[]{Afbeelding.SPOOK_BLAUW, Afbeelding.SPOOK_ROOD, Afbeelding.SPOOK_ROZE}
            )
    );

    public Speelveld(Spel spel) {
        this.spel = spel;
        this.spelStatus = SpelStatus.GEPAUZEERD;

        this.setPreferredSize(new Dimension(500, 500));
        this.setFocusable(true);
        this.requestFocus();
        this.initialiseer();
    }

    private void initialiseer() {
        this.levelManager = new LevelManager();
        this.pacman = new Pacman(this);
        this.spookjes = maakSpookjes();

        this.spel.showLevens(this.pacman.getLevens());
        this.huidigeScore = 0;
        this.spel.showScore(this.huidigeScore);
        this.spel.showLevel(1);

        this.level = this.levelManager.getLevel(this.pacman, this.spookjes);

        this.repaint();
    }

    private ArrayList<Spookje> maakSpookjes() {
        ArrayList<Spookje> nieuweSpookjes = new ArrayList<>();

        Spookje spookje1 = new Spookje(this, Afbeelding.SPOOK_ROZE);
        Spookje spookje2 = new Spookje(this, Afbeelding.SPOOK_ROOD);
        Spookje spookje3 = new Spookje(this, Afbeelding.SPOOK_BLAUW);
        Spookje spookje4 = new Spookje(this, Afbeelding.SPOOK_ROOD);

        if (levelManager.getHuidigLevelNummer() != 0) {
            spookje1.setAI(new AStar(spookje1, pacman));
            spookje2.setAI(new AStar(spookje2, pacman));
            spookje3.setAI(new RandomAI(spookje3));
            spookje4.setAI(new RandomAI(spookje4));
        }

        nieuweSpookjes.add(spookje1);
        nieuweSpookjes.add(spookje2);
        nieuweSpookjes.add(spookje3);
        nieuweSpookjes.add(spookje4);

        return nieuweSpookjes;
    }

    public void naarVolgendLevel() {

        // Level uitgespeeld
        if (levelManager.getHuidigLevelNummer() + 1 == 4) {
            pauzeer();
            JOptionPane.showMessageDialog(null, "U heeft het spel uitgespeeld!");
        } else {
            level = levelManager.getVolgendLevel(pacman, spookjes);
            this.repaint();
            this.spel.showLevel(levelManager.getHuidigLevelNummer());
        }
    }

    public void resetPositiePoppetjes() {

        int levens = pacman.getLevens();

        if (levens > 0) {
            this.spel.showLevens(levens);
            pacman.reset();
            for (Spookje spookje : spookjes) {
                spookje.reset();
            }
        } else {
            this.spel.showLevens(levens, true);
            pauzeer();
        }

    }

    public int getTotaalBolletjesInLevel() {
        return levelManager.getTotaalAantalBolletjes();
    }

    public void spawnKers() {
        while (!levelManager.getKersSpawned()) {
            int x = RandomNumberGenerator.getRandomInt(levelManager.LEVEL_SIZE);
            int y = RandomNumberGenerator.getRandomInt(levelManager.LEVEL_SIZE);

            Vakje vakje = level[x][y];
            if (vakje instanceof LeegVakje) {
                LeegVakje leegVakje = (LeegVakje) vakje;

                for (Spookje spookje : spookjes) {
                    if (spookje.getStartVakje() == leegVakje) {
                        leegVakje = null;
                        break;
                    }
                }

                if (leegVakje != null) {
                    Bolletje b = leegVakje.getBolletje();
                    if (b == null) {
                        leegVakje.toevoegenSpelElement(new Kers(leegVakje));
                        levelManager.setKersSpawned(true);
                    }
                }
            }
        }
    }
    
    public void maakSpookjesEetbaar(){
        for(Spookje spookje : spookjes){
            spookje.maakEetbaar();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.setBackground(new Color(224, 224, 224));
        for (int x = 0; x < levelManager.LEVEL_SIZE; ++x) {
            for (int y = 0; y < levelManager.LEVEL_SIZE; ++y) {
                this.level[x][y].teken((Graphics2D)g);
            }
        }
    }

    public void start() {
        this.spelStatus = SpelStatus.GESTART;
        this.addKeyListener(this.pacman);
        for (Spookje spookje : this.spookjes) {
            spookje.startAI();
        }
    }

    public void pauzeer() {
        this.spelStatus = SpelStatus.GEPAUZEERD;
        this.removeKeyListener(this.pacman);
        for (Spookje spookje : this.spookjes) {
            spookje.stopAI();
        }
    }

    public void reset() {
        this.pauzeer();
        this.initialiseer();
    }

    public SpelStatus getSpelStatus() {
        return this.spelStatus;
    }

    public void addPunten(int punten) {
        this.huidigeScore += punten;
        this.spel.showScore(this.huidigeScore);
    }
}
