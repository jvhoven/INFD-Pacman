package spel.AIs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import spel.levelelementen.LeegVakje;
import spel.spelelementen.Spookje;

public abstract class AI {
    
    private Timer timer = null;
    protected Spookje spookje = null;
    protected LeegVakje volgendVakje = null;

    public AI(final Spookje spookje) {
        this.spookje = spookje;
        this.timer = new Timer(300, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                ((Timer)e.getSource()).setDelay(300);
                AI.this.berekenVolgendVakje();
                if (AI.this.volgendVakje != null && AI.this.volgendVakje.getSpookje() == null ) {
                    spookje.beweegNaar(AI.this.volgendVakje);
                }
            }
        });
    }

    public void start() {
        this.timer.start();
    }

    public void stop() {
        this.timer.stop();
    }
    
    public void pauzeer() {
        this.timer.setDelay(1500);
    }

    public abstract void berekenVolgendVakje();

}

