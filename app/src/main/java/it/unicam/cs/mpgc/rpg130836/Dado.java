package it.unicam.cs.mpgc.rpg130836;

import java.util.Random;

public class Dado {

    private static final int FACCE_STANDARD = 6;

    private final int numeroFacce;
    private final Random random;

    public Dado() {
        this(FACCE_STANDARD);
    }

    public Dado(int numeroFacce) {
        if (numeroFacce < 2) {
            throw new IllegalArgumentException("Il dado deve avere almeno 2 facce.");
        }

        this.numeroFacce = numeroFacce;
        this.random = new Random();
    }

    public int lancia() {
        return random.nextInt(numeroFacce) + 1;
    }
}

