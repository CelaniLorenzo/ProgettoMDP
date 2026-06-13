package it.unicam.cs.mpgc.rpg130836.grafica.gioco;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import java.util.List;
import javafx.scene.control.Button;

public class GestoreCombattimentoGioco {

    private final SchermataGioco schermataGioco;
    private final Button attaccoSquadraButton;
    private final Button potenziaButton;

    public GestoreCombattimentoGioco(
            SchermataGioco schermataGioco,
            Button attaccoSquadraButton,
            Button potenziaButton
    ) {
        this.schermataGioco = schermataGioco;
        this.attaccoSquadraButton = attaccoSquadraButton;
        this.potenziaButton = potenziaButton;
    }

    public void mostraMessaggioEroeMancante() {
        schermataGioco.getLogArea()
                .appendText("Devi prima scegliere un eroe.\n");
    }

    public void mostraMessaggioNemicoMancante() {
        schermataGioco.getLogArea()
                .appendText("Non c'è nessun nemico da attaccare.\n");
    }

    public void disabilitaPotenziamento() {
        potenziaButton.setDisable(true);
    }

    public void abilitaPotenziamento() {
        potenziaButton.setDisable(false);
    }

    public void abilitaAttaccoSquadra() {
        attaccoSquadraButton.setDisable(false);
    }

    public void disabilitaAttaccoSquadra() {
        attaccoSquadraButton.setDisable(true);
    }
    public void aggiornaVitaGiocatore(int vita, int vitaMassima) {
        schermataGioco.getVitaGiocatoreLabel()
                .setText("Vita: " + vita + " / " + vitaMassima);
    }

    public void aggiornaVitaNemico(int vita, int vitaMassima) {
        schermataGioco.getVitaNemicoLabel()
                .setText("Vita: " + vita + " / " + vitaMassima);
    }
    public void aggiornaNomeNemico(String nomeNemico) {
        schermataGioco.getNomeNemicoLabel()
                .setText("Nemico: " + nomeNemico);
    }

    public void mostraNemicoSconfitto() {
        schermataGioco.getNomeNemicoLabel()
                .setText("Nemico: sconfitto");
    }

    public void scriviLog(String messaggio) {
        schermataGioco.getLogArea()
                .appendText(messaggio);
    }
    public void mostraNemicoSconfittoNelLog(String nomeNemico) {
        scriviLog(nomeNemico + " è stato sconfitto!\n");
    }

    public void mostraPotenziamentoDisponibile(String nomeEroe) {
        scriviLog(nomeEroe + " ha ottenuto un potenziamento! Premi 'Potenzia vincitore'.\n");
    }

    public void mostraSconfittaGiocatore() {
        scriviLog("Sei stato sconfitto!\n");
    }
    public boolean puoGestireNemicoSconfitto(Nemico nemico) {
        return nemico != null && !nemico.isVivo();
    }

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
    public void gestisciInterfacciaNemicoSconfitto(
            String nomeNemico,
            String nomeEroe
    ) {
        mostraNemicoSconfittoNelLog(nomeNemico);
        mostraNemicoSconfitto();
        abilitaPotenziamento();
        mostraPotenziamentoDisponibile(nomeEroe);
    }
    public boolean puoAssegnarePotenziamento(
            boolean potenziamentoDisponibile
    ) {
        return !potenziamentoDisponibile;
    }
    public void aggiornaInterfacciaEroe(
            int vita,
            int vitaMassima
    ) {
        aggiornaVitaGiocatore(vita, vitaMassima);
    }
    public void aggiornaEroeDopoPotenziamento(
            Eroe eroeAttuale,
            Eroe eroeDaPotenziare,
            Runnable aggiornaStatistiche
    ) {
        if (eroeDaPotenziare == eroeAttuale) {
            aggiornaStatistiche.run();
        }
    }
    public void terminaPotenziamento() {
        disabilitaPotenziamento();
    }
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

    public void scriviMessaggioSconfittaEroe() {
        schermataGioco.getLogArea().appendText("Sei stato sconfitto!\n");
    }
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