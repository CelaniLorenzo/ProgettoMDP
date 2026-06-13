package it.unicam.cs.mpgc.rpg130836.grafica.menu;

import it.unicam.cs.mpgc.rpg130836.grafica.Avvio.NavigazioneApplicazione;
import java.util.Objects;

public class AzioniMenu {

    private final AzioneNuovaPartita azioneNuovaPartita;
    private final AzioneCaricaPartita azioneCaricaPartita;

    public AzioniMenu(NavigazioneApplicazione navigazione) {
        this(
                new AzioneNuovaPartita(navigazione),
                new AzioneCaricaPartita(navigazione)
        );
    }

    public AzioniMenu(
            AzioneNuovaPartita azioneNuovaPartita,
            AzioneCaricaPartita azioneCaricaPartita
    ) {
        this.azioneNuovaPartita = Objects.requireNonNull(azioneNuovaPartita);
        this.azioneCaricaPartita = Objects.requireNonNull(azioneCaricaPartita);
    }

    public void nuovaPartita() {
        azioneNuovaPartita.esegui();
    }

    public void caricaPartita() {
        azioneCaricaPartita.esegui();
    }
}
