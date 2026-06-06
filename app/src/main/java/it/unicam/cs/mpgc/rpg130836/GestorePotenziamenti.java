package it.unicam.cs.mpgc.rpg130836;

import java.util.Objects;

public class GestorePotenziamenti {

    private final Output output;

    public GestorePotenziamenti(Output output) {
        this.output = Objects.requireNonNull(output, "Output non può essere null.");
    }

    public void potenziaVincitore(Combattente vincitore) {
        if (vincitore == null) {
            throw new IllegalArgumentException("Il vincitore non può essere null.");
        }

        if (vincitore instanceof Potenziabile) {
            Potenziabile potenziabile = (Potenziabile) vincitore;

            potenziabile.potenzia();

            output.stampa(
                    vincitore.getNome()
                            + " si potenzia: "
                            + potenziabile.descrizionePotenziamento()
            );
        } else {
            output.stampa(vincitore.getNome() + " non può essere potenziato.");
        }
    }
}