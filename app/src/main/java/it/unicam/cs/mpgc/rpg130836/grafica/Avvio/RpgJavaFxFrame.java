package it.unicam.cs.mpgc.rpg130836.grafica.Avvio;

import java.util.Objects;
import javafx.stage.Stage;

public class RpgJavaFxFrame implements NavigazioneApplicazione {

    private final NavigazioneApplicazione navigazione;

    public RpgJavaFxFrame(Stage stage) {
        this.navigazione = new GestoreNavigazioneApplicazione(
                Objects.requireNonNull(stage)
        );
    }

    @Override
    public void mostraMenu() {
        navigazione.mostraMenu();
    }

    @Override
    public void apriNuovaPartita() {
        navigazione.apriNuovaPartita();
    }

    @Override
    public void caricaPartitaDaMenu() {
        navigazione.caricaPartitaDaMenu();
    }

    @Override
    public void mostraSchermataGioco() {
        navigazione.mostraSchermataGioco();
    }
}
