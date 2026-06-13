package it.unicam.cs.mpgc.rpg130836.grafica.menu;

import it.unicam.cs.mpgc.rpg130836.grafica.Avvio.AvvioPartita;
import java.util.Objects;

public class AzioneNuovaPartita {

    private final AvvioPartita avvioPartita;

    /*
     * Costruisce un'istanza di AzioneNuovaPartita.
     */
    public AzioneNuovaPartita(AvvioPartita avvioPartita) {
        this.avvioPartita = Objects.requireNonNull(avvioPartita);
    }

    /*
     * Esegue l'azione associata.
     */
    public void esegui() {
        avvioPartita.apriNuovaPartita();
    }
}
