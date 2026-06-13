package it.unicam.cs.mpgc.rpg130836.model.combattimento;

import java.util.Random;

public class Dado {

    private static final int FACCE_STANDARD = 6;

    private final int numeroFacce;
    private final Random random;

    /*
     * Costruisce un'istanza di Dado.
     */
    public Dado() {
        this(FACCE_STANDARD);
    }

    /*
     * Costruisce un'istanza di Dado.
     */
    public Dado(int numeroFacce) {
        if (numeroFacce < 2) {
            throw new IllegalArgumentException("Il dado deve avere almeno 2 facce.");
        }

        this.numeroFacce = numeroFacce;
        this.random = new Random();
    }

    /*
     * Tira il dado e restituisce il risultato.
     */
    public int tira() {
        return random.nextInt(numeroFacce) + 1;
    }
}

