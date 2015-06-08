/*
 * Decompiled with CFR 0_101.
 */
package spel;

import spel.AIs.*;
import spel.AIs.SmartAI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JPanel;
import spel.enums.Afbeelding;
import spel.enums.SpelStatus;
import spel.levelelementen.Vakje;
import spel.spelelementen.Pacman;
import spel.spelelementen.Spookje;

public class Speelveld extends JPanel {
    
    private Spel spel = null;
    private SpelStatus spelStatus = null;
    private int huidigeScore = 0;
    private Vakje[][] level = null;
    private Pacman pacman = null;
    private ArrayList<Spookje> spookjes = null;
    
    LevelManager levelManager = null;
    Queue<Afbeelding> spookjesAfbeeldingen = new LinkedList<>(
        Arrays.asList(
            new Afbeelding[]{Afbeelding.SPOOK_BLAUW, Afbeelding.SPOOK_ROOD, Afbeelding.SPOOK_ROZE}
        )
    );

    public Speelveld(Spel spel) {
        this.spel = spel;
        this.spelStatus = SpelStatus.GEPAUZEERD;
        this.huidigeScore = 0;
        this.spel.showScore(this.huidigeScore);
        
        this.setPreferredSize(new Dimension(500, 500));
        this.setFocusable(true);
        this.requestFocus();
        this.initialiseer();
    }

    private void initialiseer() {
        this.levelManager = new LevelManager();
        this.pacman = new Pacman(this);
        
        Spookje spookje1 = new Spookje(this, Afbeelding.SPOOK_ROZE);
        spookje1.setAI(new RandomAI(spookje1));
        
        Spookje spookje2 = new Spookje(this, Afbeelding.SPOOK_ROOD);
        spookje2.setAI(new SmartAI(spookje2));
        
        Spookje spookje3 = new Spookje(this, Afbeelding.SPOOK_BLAUW);
        spookje3.setAI(new SmartAI(spookje3));
        
        this.spookjes = new ArrayList();
        this.spookjes.add(spookje1);
        this.spookjes.add(spookje2);
        this.spookjes.add(spookje3);
        
        this.level = this.levelManager.getLevel(this.pacman, this.spookjes);
        this.repaint();
    }

    public void resetPositiePoppetjes() {
        this.levelManager.resetPosities(this.level, this.pacman, this.spookjes);
    }

    private void teken(Graphics2D g) {
        this.setBackground(new Color(224, 224, 224));
        for (int x = 0; x < 15; ++x) {
            for (int y = 0; y < 15; ++y) {
                this.level[x][y].teken(g);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.teken((Graphics2D)g);
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
        this.huidigeScore+=punten;
        this.spel.showScore(this.huidigeScore);
    }
}

