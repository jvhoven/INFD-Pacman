package spel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import spel.levelelementen.LeegVakje;
import spel.levelelementen.Muur;
import spel.levelelementen.Positie;
import spel.levelelementen.Vakje;
import spel.spelelementen.Pacman;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jeffrey
 */
public class Speelveld extends JPanel {

    private SpelStatus status = null;
    //private Graphics2D g;
    private Vakje[][] level = null;
    private Pacman pacman = null;
    
    private Timer gameTimer = null;

    public Speelveld() {
        super();

        setPreferredSize(new Dimension(500, 500));
        setFocusable(true);
        requestFocus();

        start();
    }

    private void initialiseer() {
        status = SpelStatus.GESTART;
        gameTimer = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        //g = (Graphics2D) getGraphics();

        int[][] levelInfo = {
            {1, 1, 1, 2, 1},
            {0, 0, 0, 0, 1},
            {0, 1, 1, 1, 1},
            {0, 0, 0, 0, 1},
            {1, 1, 1, 0, 1}
        };

        initLevel(levelInfo);
    }

    public void initLevel(int[][] levelInfo) {
        level = new Vakje[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {                
                Positie nieuwePositie = new Positie(i + 1, j + 1);
                Vakje nieuwVakje = null;
                if (levelInfo[i][j] == 1) {
                    nieuwVakje = new Muur(nieuwePositie);
                } else if (levelInfo[i][j] == 0) {
                    nieuwVakje = new LeegVakje(nieuwePositie);
                } else if (levelInfo[i][j] == 2) {
                    nieuwVakje = new LeegVakje(nieuwePositie);
                    initPacman((LeegVakje) nieuwVakje);
                    ((LeegVakje) nieuwVakje).toevoegenPoppetje(pacman);
                } else if (levelInfo[i][j] == 3) {
                    nieuwVakje = new LeegVakje(nieuwePositie);
                    //stop spookje in dit vakje
                }
                level[i][j] = nieuwVakje;
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                level[i][j].setBuurVakjes(level);
            }
        }
    }

    public void initPacman(LeegVakje startVakje) {
        this.pacman = new Pacman(startVakje);
        this.addKeyListener(pacman);
        //this.pacman.requestFocus();
    }

    private void start() {

        initialiseer();
        // Game loop komt hier
        
        gameTimer.start();
        
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
}
