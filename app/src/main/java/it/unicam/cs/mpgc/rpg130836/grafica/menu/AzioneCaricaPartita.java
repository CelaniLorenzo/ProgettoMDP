package it.unicam.cs.mpgc.rpg130836.grafica.menu;

import it.unicam.cs.mpgc.rpg130836.grafica.Avvio.CaricamentoPartita;
import java.util.Objects;

public class AzioneCaricaPartita {

    private final CaricamentoPartita caricamentoPartita;

    /*
     * Costruisce un'istanza di AzioneCaricaPartita.
     */
    public AzioneCaricaPartita(CaricamentoPartita caricamentoPartita) {
        this.caricamentoPartita = Objects.requireNonNull(caricamentoPartita);
    }

    /*
     * Esegue l'azione associata.
     */
    public void esegui() {
        caricamentoPartita.caricaPartitaDaMenu();
    }
}
