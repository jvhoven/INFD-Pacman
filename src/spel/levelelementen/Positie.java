/*
 * Decompiled with CFR 0_101.
 */
package spel.levelelementen;

public class Positie {
    public int x;
    public int y;

    public Positie(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Positie p) {
        return this.x == p.x && this.y == p.y;
    }
}

