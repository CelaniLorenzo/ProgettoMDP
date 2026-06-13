package it.unicam.cs.mpgc.rpg130836.model.abilita;

import it.unicam.cs.mpgc.rpg130836.model.output.Output;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Combattente;
import java.util.Objects;

public class GestorePotenziamenti {

    private final Output output;

    /*
     * Costruisce un'istanza di GestorePotenziamenti.
     */
    public GestorePotenziamenti(Output output) {
        this.output = Objects.requireNonNull(output, "Output non può essere null.");
    }

    /*
     * Potenzia il vincitore.
     */
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