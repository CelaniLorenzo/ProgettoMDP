package it.unicam.cs.mpgc.rpg130836;

import java.util.Random;

public class Dado {
    private final int facce;
    private final Random random;

    public Dado(int facce) {
        if (facce <= 0) {
            throw new IllegalArgumentException("Il dado deve avere almeno una faccia");
        }
        this.facce = facce;
        this.random = new Random();
    }

    public int tira() {
        return random.nextInt(facce) + 1;
    }
}




