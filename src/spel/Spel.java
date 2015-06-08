/*
 * Decompiled with CFR 0_101.
 */
package spel;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JLabel;
import spel.GUI;
import spel.Speelveld;
import spel.enums.SpelStatus;

public class Spel
extends JFrame {
    private final int BREEDTE = 661;
    private final int HOOGTE = 719;
    Speelveld speelveld = null;
    GUI gui = null;

    public Spel() {
        super("INF-D Pacman Groep B6");
        this.setSize(661, 719);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
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
}

