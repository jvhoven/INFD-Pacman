/*
 * Decompiled with CFR 0_101.
 */
package spel.enums;

public enum Richting {
    NEUTRAAL(0),
    LINKS(180),
    RECHTS(360),
    OMHOOG(270),
    OMLAAG(90);
    
    private int helling;

    private Richting(int graden) {
        this.helling = graden;
    }

    public double getGraden() {
        return Math.toRadians(this.helling);
    }
}

