/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spel.levelelementen;

/**
 *
 * @author Hans
 */
public class Positie {
    public int x;
    public int y;
    
    public Positie(int x, int y){
        this.x = x;
        this.y = y;
    }
            
    public boolean equals(Positie p){
        return this.x == p.x && this.y == p.y;
    }
}
