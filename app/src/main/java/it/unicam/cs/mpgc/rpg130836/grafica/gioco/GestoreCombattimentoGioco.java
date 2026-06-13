package it.unicam.cs.mpgc.rpg130836.grafica.gioco;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import java.util.List;
import javafx.scene.control.Button;

public class GestoreCombattimentoGioco {

    private final SchermataGioco schermataGioco;
    private final Button attaccoSquadraButton;
    private final Button potenziaButton;

    /*
     * Costruisce un'istanza di GestoreCombattimentoGioco.
     */
    public GestoreCombattimentoGioco(
            SchermataGioco schermataGioco,
            Button attaccoSquadraButton,
            Button potenziaButton
    ) {
        this.schermataGioco = schermataGioco;
        this.attaccoSquadraButton = attaccoSquadraButton;
        this.potenziaButton = potenziaButton;
    }

    /*
     * Mostra il messaggio eroe mancante.
     */
    public void mostraMessaggioEroeMancante() {
        schermataGioco.getLogArea()
                .appendText("Devi prima scegliere un eroe.\n");
    }

    /*
     * Mostra il messaggio nemico mancante.
     */
    public void mostraMessaggioNemicoMancante() {
        schermataGioco.getLogArea()
                .appendText("Non c'è nessun nemico da attaccare.\n");
    }

    /*
     * Disabilita il potenziamento.
     */
    public void disabilitaPotenziamento() {
        potenziaButton.setDisable(true);
    }

    /*
     * Abilita il potenziamento.
     */
    public void abilitaPotenziamento() {
        potenziaButton.setDisable(false);
    }

    /*
     * Abilita l'attacco di squadra.
     */
    public void abilitaAttaccoSquadra() {
        attaccoSquadraButton.setDisable(false);
    }

    /*
     * Disabilita l'attacco di squadra.
     */
    public void disabilitaAttaccoSquadra() {
        attaccoSquadraButton.setDisable(true);
    }

    /*
     * Aggiorna la vita del giocatore.
     */
    public void aggiornaVitaGiocatore(int vita, int vitaMassima) {
        schermataGioco.getVitaGiocatoreLabel()
                .setText("Vita: " + vita + " / " + vitaMassima);
    }

    /*
     * Aggiorna la vita del nemico.
     */
    public void aggiornaVitaNemico(int vita, int vitaMassima) {
        schermataGioco.getVitaNemicoLabel()
                .setText("Vita: " + vita + " / " + vitaMassima);
    }

    /*
     * Aggiorna il nome del nemico.
     */
    public void aggiornaNomeNemico(String nomeNemico) {
        schermataGioco.getNomeNemicoLabel()
                .setText("Nemico: " + nomeNemico);
    }

    /*
     * Mostra il nemico sconfitto.
     */
    public void mostraNemicoSconfitto() {
        schermataGioco.getNomeNemicoLabel()
                .setText("Nemico: sconfitto");
    }

    /*
     * Scrive un messaggio nel log.
     */
    public void scriviLog(String messaggio) {
        schermataGioco.getLogArea()
                .appendText(messaggio);
    }

    /*
     * Scrive nel log che il nemico è stato sconfitto.
     */
    public void mostraNemicoSconfittoNelLog(String nomeNemico) {
        scriviLog(nomeNemico + " è stato sconfitto!\n");
    }

    /*
     * Mostra il messaggio del potenziamento disponibile.
     */
    public void mostraPotenziamentoDisponibile(String nomeEroe) {
        scriviLog(nomeEroe + " ha ottenuto un potenziamento! Premi 'Potenzia vincitore'.\n");
    }

    /*
     * Mostra la sconfitta del giocatore.
     */
    public void mostraSconfittaGiocatore() {
        scriviLog("Sei stato sconfitto!\n");
    }

    /*
     * Controlla se è possibile gestire il nemico sconfitto.
     */
    public boolean puoGestireNemicoSconfitto(Nemico nemico) {
        return nemico != null && !nemico.isVivo();
    }

    /*
     * Controlla se è possibile applicare il potenziamento.
     */
    public boolean puoPotenziare(
            boolean potenziamentoDisponibile,
            Eroe eroeDaPotenziare
    ) {
        if (!potenziamentoDisponibile) {
            schermataGioco.getLogArea()
                    .appendText("Non hai potenziamenti disponibili.\n");
            return false;
        }

        if (eroeDaPotenziare == null) {
            schermataGioco.getLogArea()
                    .appendText("Non c'è nessun vincitore da potenziare.\n");
            return false;
        }

        return true;
    }

    /*
     * Gestisce l'interfaccia dopo la sconfitta del nemico.
     */
    public void gestisciInterfacciaNemicoSconfitto(
            String nomeNemico,
            String nomeEroe
    ) {
        mostraNemicoSconfittoNelLog(nomeNemico);
        mostraNemicoSconfitto();
        abilitaPotenziamento();
        mostraPotenziamentoDisponibile(nomeEroe);
    }

    /*
     * Controlla se è possibile assegnare un potenziamento.
     */
    public boolean puoAssegnarePotenziamento(
            boolean potenziamentoDisponibile
    ) {
        return !potenziamentoDisponibile;
    }

    /*
     * Aggiorna l'interfaccia dell'eroe.
     */
    public void aggiornaInterfacciaEroe(
            int vita,
            int vitaMassima
    ) {
        aggiornaVitaGiocatore(vita, vitaMassima);
    }

    /*
     * Aggiorna l'eroe dopo potenziamento.
     */
    public void aggiornaEroeDopoPotenziamento(
            Eroe eroeAttuale,
            Eroe eroeDaPotenziare,
            Runnable aggiornaStatistiche
    ) {
        if (eroeDaPotenziare == eroeAttuale) {
            aggiornaStatistiche.run();
        }
    }

    /*
     * Termina la fase di potenziamento.
     */
    public void terminaPotenziamento() {
        disabilitaPotenziamento();
    }

    /*
     * Controlla se l'attacco è valido.
     */
    public boolean attaccoValido(Eroe eroeAttuale,
                                 boolean eroeSelezionato,
                                 Nemico nemicoAttuale) {
        if (!eroeSelezionato || eroeAttuale == null) {
            mostraMessaggioEroeMancante();
            return false;
        }

        if (nemicoAttuale == null || !nemicoAttuale.isVivo()) {
            mostraMessaggioNemicoMancante();
            return false;
        }

        if (!eroeAttuale.isVivo()) {
            mostraSconfittaGiocatore();
            return false;
        }

        return true;
    }

    /*
     * Aggiorna il vite dopo attacco.
     */
    public void aggiornaViteDopoAttacco(Eroe eroeAttuale,
                                        Nemico nemicoAttuale) {
        aggiornaVitaGiocatore(
                eroeAttuale.getVita(),
                eroeAttuale.getVitaMassima()
        );

        aggiornaVitaNemico(
                nemicoAttuale.getVita(),
                nemicoAttuale.getVitaMassima()
        );
    }

    /*
     * Scrive il messaggio di sconfitta dell'eroe.
     */
    public void scriviMessaggioSconfittaEroe() {
        schermataGioco.getLogArea().appendText("Sei stato sconfitto!\n");
    }

    /*
     * Gestisce il nemico sconfitto e il potenziamento.
     */
    public boolean gestisciNemicoSconfittoConPotenziamento(
            Nemico nemicoAttuale,
            Eroe eroeAttuale,
            boolean potenziamentoDisponibile
    ) {
        if (!puoGestireNemicoSconfitto(nemicoAttuale)) {
            return false;
        }

        if (!puoAssegnarePotenziamento(potenziamentoDisponibile)) {
            return false;
        }

        gestisciInterfacciaNemicoSconfitto(
                nemicoAttuale.getNome(),
                eroeAttuale.getNome()
        );

        return true;
    }

    /*
     * Prepara il nuovo incontro.
     */
    public Nemico preparaNuovoIncontro(
            int numeroIncontro,
            List<Nemico> nemiciDisponibili
    ) {
        Nemico nemico = nemiciDisponibili.get(numeroIncontro - 1);

        abilitaAttaccoSquadra();
        disabilitaPotenziamento();

        aggiornaNomeNemico(nemico.getNome());
        aggiornaVitaNemico(
                nemico.getVita(),
                nemico.getVitaMassima()
        );

        scriviLog("Incontro " + numeroIncontro
                + ": compare " + nemico.getNome() + "!\n");

        return nemico;
    }

}