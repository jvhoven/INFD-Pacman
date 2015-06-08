/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spel.enums;

/**
 *
 * @author Jeffrey
 */
public enum Richting {
    
    NEUTRAAL(0), LINKS(180), RECHTS(360), OMHOOG(270), OMLAAG(90);
    private int helling;
    
    private Richting(int graden) {
        this.helling = graden;
    }
    
    public double getGraden() {
        return Math.toRadians(this.helling);
    }
}
