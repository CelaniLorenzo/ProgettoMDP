package it.unicam.cs.mpgc.rpg130836.grafica.menu;

import it.unicam.cs.mpgc.rpg130836.grafica.Avvio.AvvioPartita;
import java.util.Objects;

public class AzioneNuovaPartita {

    private final AvvioPartita avvioPartita;

    public AzioneNuovaPartita(AvvioPartita avvioPartita) {
        this.avvioPartita = Objects.requireNonNull(avvioPartita);
    }

    public void esegui() {
        avvioPartita.apriNuovaPartita();
    }
}
