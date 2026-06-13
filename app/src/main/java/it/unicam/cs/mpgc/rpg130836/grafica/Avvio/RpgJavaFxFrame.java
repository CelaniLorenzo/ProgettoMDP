package it.unicam.cs.mpgc.rpg130836.grafica.Avvio;

import java.util.Objects;
import javafx.stage.Stage;

public class RpgJavaFxFrame implements NavigazioneApplicazione {

    private final NavigazioneApplicazione navigazione;

    /*
     * Costruisce il frame principale dell'applicazione
     * e inizializza il gestore della navigazione.
     */
    public RpgJavaFxFrame(Stage stage) {
        this.navigazione = new GestoreNavigazioneApplicazione(
                Objects.requireNonNull(stage)
        );
    }

    /*
     * Mostra il menu principale dell'applicazione.
     */
    @Override
    public void mostraMenu() {
        navigazione.mostraMenu();
    }

    /*
     * Avvia una nuova partita.
     */
    @Override
    public void apriNuovaPartita() {
        navigazione.apriNuovaPartita();
    }

    /*
     * Carica una partita salvata dal menu.
     */
    @Override
    public void caricaPartitaDaMenu() {
        navigazione.caricaPartitaDaMenu();
    }

    /*
     * Mostra la schermata principale della partita.
     */
    @Override
    public void mostraSchermataGioco() {
        navigazione.mostraSchermataGioco();
    }
}