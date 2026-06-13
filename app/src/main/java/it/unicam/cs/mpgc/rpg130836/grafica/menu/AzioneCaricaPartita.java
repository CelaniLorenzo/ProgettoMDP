package it.unicam.cs.mpgc.rpg130836.grafica.menu;

import it.unicam.cs.mpgc.rpg130836.grafica.Avvio.CaricamentoPartita;
import java.util.Objects;

public class AzioneCaricaPartita {

    private final CaricamentoPartita caricamentoPartita;

    public AzioneCaricaPartita(CaricamentoPartita caricamentoPartita) {
        this.caricamentoPartita = Objects.requireNonNull(caricamentoPartita);
    }

    public void esegui() {
        caricamentoPartita.caricaPartitaDaMenu();
    }
}
