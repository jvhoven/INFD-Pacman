/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.Random;

/**
 *
 * @author Hans
 */
public class RandomNumberGenerator {

    public static int getRandomInt(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }
}
