package it.unicam.cs.mpgc.rpg130836.grafica.Avvio;

import it.unicam.cs.mpgc.rpg130836.grafica.gioco.GestoreAperturaGioco;
import it.unicam.cs.mpgc.rpg130836.grafica.gioco.GestoreSchermataPartita;
import it.unicam.cs.mpgc.rpg130836.grafica.mappa.GestoreCombattimentoMappaPartita;
import it.unicam.cs.mpgc.rpg130836.grafica.mappa.GestoreMappaPartita;
import it.unicam.cs.mpgc.rpg130836.grafica.menu.GestoreApplicazione;
import javafx.stage.Stage;

public class GestoreNavigazioneApplicazione implements NavigazioneApplicazione {

    private final DatiApplicazioneRpg dati;
    private final GestoreSchermataPartita gestoreSchermataPartita;
    private final GestoreMappaPartita gestoreMappaPartita;

    /*
     * Costruisce il gestore della navigazione principale
     * dell'applicazione e inizializza i gestori delle schermate.
     */
    public GestoreNavigazioneApplicazione(Stage stage) {
        this.dati = new DatiApplicazioneRpg(stage);

        GestoreCombattimentoMappaPartita gestoreCombattimentoMappaPartita =
                new GestoreCombattimentoMappaPartita(dati);

        this.gestoreMappaPartita =
                new GestoreMappaPartita(
                        dati,
                        this::mostraMenu,
                        gestoreCombattimentoMappaPartita
                );

        this.gestoreSchermataPartita =
                new GestoreSchermataPartita(
                        dati,
                        this::mostraMenu,
                        gestoreMappaPartita::mostraMappaGioco
                );
    }

    /*
     * Mostra il menu principale dell'applicazione.
     */
    @Override
    public void mostraMenu() {
        GestoreApplicazione gestoreApplicazione =
                new GestoreApplicazione(this);

        gestoreApplicazione.avvia(dati.stage);
    }

    /*
     * Avvia una nuova partita, resettando lo stato precedente
     * e mostrando la mappa di gioco.
     */
    @Override
    public void apriNuovaPartita() {
        gestoreSchermataPartita.resettaPartita();
        gestoreMappaPartita.mostraMappaGioco();
    }

    /*
     * Carica una partita salvata dal menu
     * e aggiorna la schermata di gioco con i dati caricati.
     */
    @Override
    public void caricaPartitaDaMenu() {
        GestoreAperturaGioco gestoreAperturaGioco =
                new GestoreAperturaGioco(this);

        gestoreAperturaGioco.apriGioco();
        gestoreSchermataPartita.caricaPartitaDaFile();
    }

    /*
     * Mostra la schermata principale della partita.
     */
    @Override
    public void mostraSchermataGioco() {
        gestoreSchermataPartita.mostraSchermataGioco();
    }
}