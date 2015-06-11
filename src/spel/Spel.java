package spel;

import java.awt.Component;
import javax.swing.JFrame;
import spel.enums.SpelStatus;

public class Spel extends JFrame {
    
    private final int BREEDTE = 661;
    private final int HOOGTE = 719;
    
    Speelveld speelveld = null;
    GUI gui = null;

    public Spel() {
        super("INF-D Pacman Groep B6");
        this.setSize(661, 719);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.initialiseer();
    }

    private void initialiseer() {
        this.gui = new GUI(this);
        this.add((Component)this.gui, "North");
        this.speelveld = new Speelveld(this);
        this.add((Component)this.speelveld, "Center");
        this.setVisible(true);
    }

    public String setSpelStatus() {
        if (this.speelveld.getSpelStatus() == SpelStatus.GESTART) {
            this.speelveld.pauzeer();
            return "Hervat";
        }
        this.speelveld.start();
        return "Pauzeer";
    }

    public void reset() {
        this.speelveld.reset();
    }

    public void showScore(int huidigeScore) {
        this.gui.scoreLabel.setText("Score: " + Integer.toString(huidigeScore));
    }
    
    public void showLevel(int level) {
        this.gui.levelLabel.setText("Level: " + Integer.toString(level));
    }
    
    public void showLevens(int levens, boolean...gameOver) {
        if(gameOver.length > 0) {
            this.gui.levensLabel.setText("GAME OVER");
        } else {
            this.gui.levensLabel.setText("Levens: " + Integer.toString(levens));
        }
    }
}

