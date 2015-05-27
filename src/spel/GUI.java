package spel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jeffrey
 */
public class GUI extends JPanel {

    JButton startButton = null;
    JButton resetButton = null;

    public GUI(Spel spel) {
        startButton = new JButton("Start");
        startButton.setFocusable(false);
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setText(spel.setSpelStatus());
            }
        });
        this.add(startButton);

        resetButton = new JButton("Reset");
        resetButton.setFocusable(false);
        resetButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                spel.reset();
                startButton.setText("Start");
            }
        });
        this.add(resetButton);

        this.setFocusable(false);
    }
}
