package it.unicam.cs.mpgc.rpg130836.grafica.gioco;

import it.unicam.cs.mpgc.rpg130836.grafica.Avvio.DatiApplicazioneRpg;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import it.unicam.cs.mpgc.rpg130836.model.stato.DatiResetPartita;
import it.unicam.cs.mpgc.rpg130836.model.stato.StatoGioco;
import java.util.Objects;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class GestoreSchermataPartita {

    private final DatiApplicazioneRpg dati;
    private final Runnable mostraMenu;
    private final Runnable mostraMappa;

    public GestoreSchermataPartita(
            DatiApplicazioneRpg dati,
            Runnable mostraMenu,
            Runnable mostraMappa
    ) {
        this.dati = Objects.requireNonNull(dati);
        this.mostraMenu = Objects.requireNonNull(mostraMenu);
        this.mostraMappa = Objects.requireNonNull(mostraMappa);
    }

    public void mostraSchermataGioco() {
        dati.schermataGioco.mostra(dati.stage, dati.nomeGiocatoreAttuale);

        Label scegliEroeLabel =
                dati.aperturaSchermataGioco.creaEtichettaSceltaEroe(
                        dati.schermataGioco
                );

        DatiPersonaggiGioco datiPersonaggiGioco =
                dati.aperturaSchermataGioco.creaPersonaggi(
                        dati.controllerGioco
                );

        applicaDatiPersonaggiGioco(datiPersonaggiGioco);

        HBox sceltaEroi = creaSceltaEroiSchermataGioco();

        creaPulsantiSchermataGioco();
        configuraAzioniSchermataGioco();
        inizializzaCombattimentoSchermataGioco();
        configuraLayoutSchermataGioco(scegliEroeLabel, sceltaEroi);
    }

    public void caricaPartitaDaFile() {
        StatoGioco statoGioco =
                dati.controllerSalvataggio.carica(
                        dati.schermataGioco.getLogArea()
                );

        if (statoGioco == null) {
            return;
        }

        aggiornaVariabiliDaStatoGioco(statoGioco);
        dati.controllerSalvataggio.applicaStatoCaricato(
                statoGioco,
                dati.schermataGioco,
                dati.eroiDisponibili,
                dati.nemiciDisponibili,
                dati.attaccoSquadraButton
        );
        dati.eroeAttuale =
                dati.gestoreSalvataggioPartita.trovaEroePerNome(
                        dati.statoPartitaCorrente.getNomeGiocatoreAttuale(),
                        dati.eroiDisponibili
                );

        dati.nemicoAttuale =
                dati.gestoreSalvataggioPartita.trovaNemicoPerNome(
                        dati.statoPartitaCorrente.getNomeNemicoAttuale(),
                        dati.nemiciDisponibili
                );

        dati.statoPartitaCorrente.setEroeAttuale(dati.eroeAttuale);
        dati.statoPartitaCorrente.setNemicoAttuale(dati.nemicoAttuale);

        dati.eroeSelezionato =
                dati.statoPartitaCorrente.getEroeAttuale() != null;
        dati.statoPartitaCorrente.setEroeSelezionato(dati.eroeSelezionato);
    }

    public void resettaPartita() {
        DatiResetPartita datiResetPartita =
                dati.gestoreResetPartita.resettaPartita();

        applicaDatiResetPartita(datiResetPartita);
        dati.statoPartitaCorrente.setEroeAttuale(null);
        dati.statoPartitaCorrente.setNemicoAttuale(null);
        dati.statoPartitaCorrente.setEroeDaPotenziare(null);
        dati.statoPartitaCorrente.setPotenziamentoDisponibile(false);
        dati.statoPartitaCorrente.setPotenziamentoDiSquadra(false);
    }

    private void applicaDatiResetPartita(DatiResetPartita datiResetPartita) {
        dati.statoPartitaCorrente.aggiornaDaReset(datiResetPartita);
        dati.numeroIncontro = datiResetPartita.getNumeroIncontro();

        dati.nomeNemicoAttuale = datiResetPartita.getNomeNemicoAttuale();
        dati.vitaNemicoAttuale = datiResetPartita.getVitaNemicoAttuale();
        dati.vitaNemicoMassima = datiResetPartita.getVitaNemicoMassima();

        dati.vitaGiocatoreAttuale =
                datiResetPartita.getVitaGiocatoreAttuale();
        dati.vitaGiocatoreMassima =
                datiResetPartita.getVitaGiocatoreMassima();
        dati.nomeGiocatoreAttuale =
                datiResetPartita.getNomeGiocatoreAttuale();

        dati.dannoGiocatore = datiResetPartita.getDannoGiocatore();
        dati.eroeSelezionato = datiResetPartita.isEroeSelezionato();
        dati.giocatoreAttaccaPerPrimo =
                datiResetPartita.isGiocatoreAttaccaPerPrimo();
        dati.attaccoSquadraUsato = datiResetPartita.isAttaccoSquadraUsato();
        dati.potenziamentoDisponibile =
                datiResetPartita.isPotenziamentoDisponibile();
        dati.eroeDaPotenziare = datiResetPartita.getEroeDaPotenziare();
        dati.eroeAttuale = datiResetPartita.getEroeAttuale();
        dati.nemicoAttuale = datiResetPartita.getNemicoAttuale();
        dati.eroiDisponibili = datiResetPartita.getEroi();
        dati.nemiciDisponibili = datiResetPartita.getNemici();
        dati.eroeMappa = null;
    }

    private void applicaDatiPersonaggiGioco(DatiPersonaggiGioco personaggi) {
        dati.eroiDisponibili = personaggi.getEroi();
        dati.nemiciDisponibili = personaggi.getNemici();
    }

    private HBox creaSceltaEroiSchermataGioco() {
        return dati.aperturaSchermataGioco.creaSceltaEroi(
                dati.gestoreSchermataGioco,
                dati.eroiDisponibili,
                () -> selezionaEroe(dati.eroiDisponibili.get(0)),
                () -> selezionaEroe(dati.eroiDisponibili.get(1)),
                () -> selezionaEroe(dati.eroiDisponibili.get(2))
        );
    }

    private void creaPulsantiSchermataGioco() {
        PulsantiCombattimentoGioco pulsanti =
                dati.aperturaSchermataGioco.creaPulsantiCombattimento(
                        dati.gestoreSchermataGioco,
                        this::eseguiAttaccoSquadra,
                        this::potenziaVincitore
                );

        dati.attaccoSquadraButton = pulsanti.getAttaccoSquadraButton();
        dati.potenziaButton = pulsanti.getPotenziaButton();
    }

    private void configuraAzioniSchermataGioco() {
        dati.aperturaSchermataGioco.configuraAzioniPulsanti(
                dati.stage,
                dati.gestoreSchermataGioco,
                this::gestisciProssimoIncontro,
                this::gestisciAttacco,
                this::salvaPartita,
                this::gestisciAperturaMappa,
                mostraMenu
        );
    }

    private void inizializzaCombattimentoSchermataGioco() {
        DatiCombattimentoGioco datiCombattimento =
                dati.aperturaSchermataGioco.creaDatiCombattimentoGioco(
                        dati.schermataGioco,
                        dati.attaccoSquadraButton,
                        dati.potenziaButton,
                        dati.dado
                );

        dati.gestoreCombattimentoGioco =
                datiCombattimento.getGestoreCombattimentoGioco();
        dati.gestorePotenziamentiGioco =
                datiCombattimento.getGestorePotenziamentiGioco();
        dati.combattimento = datiCombattimento.getCombattimento();
    }

    private void configuraLayoutSchermataGioco(
            Label scegliEroeLabel,
            HBox sceltaEroi
    ) {
        dati.aperturaSchermataGioco.configuraLayout(
                dati.gestoreSchermataGioco,
                scegliEroeLabel,
                sceltaEroi,
                dati.attaccoSquadraButton,
                dati.potenziaButton
        );
    }

    private void gestisciProssimoIncontro() {
        dati.gestoreSchermataGioco.gestisciProssimoIncontro(
                dati.statoPartitaCorrente.isEroeSelezionato(),
                dati.statoPartitaCorrente.getVitaNemicoAttuale(),
                dati.statoPartitaCorrente.getNumeroIncontro(),
                dati.nemiciDisponibili.size(),
                this::avviaNuovoIncontro
        );
    }

    private void avviaNuovoIncontro() {
        dati.nemicoAttuale =
                dati.controllerGioco.avviaNuovoIncontro(
                        dati.statoPartitaCorrente,
                        dati.gestoreCombattimentoGioco,
                        dati.nemiciDisponibili
                );

        dati.numeroIncontro = dati.statoPartitaCorrente.getNumeroIncontro();
        dati.nomeNemicoAttuale =
                dati.statoPartitaCorrente.getNomeNemicoAttuale();
        dati.vitaNemicoAttuale =
                dati.statoPartitaCorrente.getVitaNemicoAttuale();
        dati.vitaNemicoMassima =
                dati.statoPartitaCorrente.getVitaNemicoMassima();
    }

    private void gestisciAttacco() {
        boolean nemicoSconfitto =
                dati.controllerGioco.gestisciAttacco(
                        dati.statoPartitaCorrente,
                        dati.combattimento,
                        dati.gestoreCombattimentoGioco
                );

        dati.vitaGiocatoreAttuale =
                dati.statoPartitaCorrente.getEroeAttuale().getVita();
        dati.vitaGiocatoreMassima =
                dati.statoPartitaCorrente.getEroeAttuale().getVitaMassima();
        dati.vitaNemicoAttuale =
                dati.statoPartitaCorrente.getNemicoAttuale().getVita();
        dati.vitaNemicoMassima =
                dati.statoPartitaCorrente.getNemicoAttuale().getVitaMassima();

        if (nemicoSconfitto) {
            gestisciNemicoSconfitto();
        }
    }

    private void gestisciAperturaMappa() {
        if (dati.vitaNemicoAttuale > 0
                && dati.nemicoAttuale != null
                && dati.nemicoAttuale.isVivo()) {
            dati.schermataGioco.getLogArea()
                    .appendText("Devi prima sconfiggere il nemico attuale.\n");
            return;
        }

        mostraMappa.run();
    }

    private void salvaPartita() {
        dati.controllerSalvataggio.salvaPartita(
                dati.statoPartitaCorrente.getNumeroIncontro(),
                dati.statoPartitaCorrente.isEroeSelezionato(),
                dati.statoPartitaCorrente.isAttaccoSquadraUsato(),
                dati.statoPartitaCorrente.getNomeGiocatoreAttuale(),
                dati.statoPartitaCorrente.getVitaGiocatoreAttuale(),
                dati.statoPartitaCorrente.getVitaGiocatoreMassima(),
                dati.statoPartitaCorrente.getDannoGiocatore(),
                dati.statoPartitaCorrente.getNomeNemicoAttuale(),
                dati.statoPartitaCorrente.getVitaNemicoAttuale(),
                dati.statoPartitaCorrente.getVitaNemicoMassima(),
                dati.schermataGioco.getLogArea()
        );
    }

    private void aggiornaVariabiliDaStatoGioco(StatoGioco statoGioco) {
        dati.statoPartitaCorrente.aggiornaDaStatoGioco(statoGioco);
        dati.numeroIncontro = dati.statoPartitaCorrente.getNumeroIncontro();
        dati.eroeSelezionato =
                dati.statoPartitaCorrente.isEroeSelezionato();
        dati.attaccoSquadraUsato =
                dati.statoPartitaCorrente.isAttaccoSquadraUsato();

        dati.nomeGiocatoreAttuale =
                dati.statoPartitaCorrente.getNomeGiocatoreAttuale();
        dati.vitaGiocatoreAttuale =
                dati.statoPartitaCorrente.getVitaGiocatoreAttuale();
        dati.vitaGiocatoreMassima =
                dati.statoPartitaCorrente.getVitaGiocatoreMassima();
        dati.dannoGiocatore =
                dati.statoPartitaCorrente.getDannoGiocatore();

        dati.nomeNemicoAttuale =
                dati.statoPartitaCorrente.getNomeNemicoAttuale();
        dati.vitaNemicoAttuale =
                dati.statoPartitaCorrente.getVitaNemicoAttuale();
        dati.vitaNemicoMassima =
                dati.statoPartitaCorrente.getVitaNemicoMassima();
    }

    private void selezionaEroe(Eroe eroe) {
        dati.eroeAttuale = eroe;
        dati.statoPartitaCorrente.aggiornaEroeSelezionato(eroe);
        dati.controllerGioco.aggiornaSchermataDopoSelezioneEroe(
                dati.statoPartitaCorrente,
                dati.schermataGioco
        );
    }

    private void eseguiAttaccoSquadra() {
        boolean nemicoSconfitto =
                dati.controllerGioco.eseguiAttaccoSquadra(
                        dati.eroiDisponibili,
                        dati.statoPartitaCorrente,
                        dati.schermataGioco,
                        dati.gestoreCombattimentoGioco
                );

        if (nemicoSconfitto) {
            gestisciNemicoSconfitto();
        }
    }

    private void gestisciNemicoSconfitto() {
        dati.controllerGioco.gestisciNemicoSconfitto(
                dati.statoPartitaCorrente,
                dati.gestoreCombattimentoGioco
        );

        dati.potenziamentoDisponibile =
                dati.statoPartitaCorrente.isPotenziamentoDisponibile();
        dati.eroeDaPotenziare =
                dati.statoPartitaCorrente.getEroeDaPotenziare();
    }

    private void potenziaVincitore() {
        dati.controllerGioco.potenziaVincitore(
                dati.statoPartitaCorrente,
                dati.gestoreCombattimentoGioco,
                dati.gestorePotenziamentiGioco
        );

        dati.dannoGiocatore =
                dati.statoPartitaCorrente.getEroeAttuale().calcolaAttacco();
        dati.vitaGiocatoreAttuale =
                dati.statoPartitaCorrente.getEroeAttuale().getVita();
        dati.vitaGiocatoreMassima =
                dati.statoPartitaCorrente.getEroeAttuale().getVitaMassima();
    }
}
