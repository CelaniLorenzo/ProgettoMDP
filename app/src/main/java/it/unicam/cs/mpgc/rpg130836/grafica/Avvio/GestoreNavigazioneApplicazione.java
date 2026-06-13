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

    @Override
    public void mostraMenu() {
        GestoreApplicazione gestoreApplicazione =
                new GestoreApplicazione(this);

        gestoreApplicazione.avvia(dati.stage);
    }

    @Override
    public void apriNuovaPartita() {
        gestoreSchermataPartita.resettaPartita();
        gestoreMappaPartita.mostraMappaGioco();
    }

    @Override
    public void caricaPartitaDaMenu() {
        GestoreAperturaGioco gestoreAperturaGioco =
                new GestoreAperturaGioco(this);

        gestoreAperturaGioco.apriGioco();
        gestoreSchermataPartita.caricaPartitaDaFile();
    }

    @Override
    public void mostraSchermataGioco() {
        gestoreSchermataPartita.mostraSchermataGioco();
    }
}
