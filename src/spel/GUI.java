package spel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends JPanel {
    
    public JButton startButton = new JButton("Start");
    public JButton resetButton = null;
    public JLabel scoreLabel = null;

    public GUI(final Spel spel) {
        this.startButton.setFocusable(false);
        this.startButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.this.startButton.setText(spel.setSpelStatus());
            }
        });
        this.add(this.startButton);
        this.resetButton = new JButton("Reset");
        this.resetButton.setFocusable(false);
        this.resetButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                spel.reset();
                GUI.this.startButton.setText("Start");
            }
        });
        this.add(this.resetButton);
        this.scoreLabel = new JLabel("Score: 0");
        this.add(this.scoreLabel);
        this.setFocusable(false);
    }

}

