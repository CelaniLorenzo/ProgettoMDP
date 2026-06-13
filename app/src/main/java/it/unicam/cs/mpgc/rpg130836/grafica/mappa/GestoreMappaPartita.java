package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import it.unicam.cs.mpgc.rpg130836.grafica.Avvio.DatiApplicazioneRpg;
import java.util.Objects;
import javafx.scene.layout.Pane;

public class GestoreMappaPartita {

    private final DatiApplicazioneRpg dati;
    private final Runnable mostraMenu;
    private final GestoreCombattimentoMappaPartita gestoreCombattimento;

    /*
     * Costruisce un'istanza di GestoreMappaPartita.
     */
    public GestoreMappaPartita(
            DatiApplicazioneRpg dati,
            Runnable mostraMenu,
            GestoreCombattimentoMappaPartita gestoreCombattimento
    ) {
        this.dati = Objects.requireNonNull(dati);
        this.mostraMenu = Objects.requireNonNull(mostraMenu);
        this.gestoreCombattimento =
                Objects.requireNonNull(gestoreCombattimento);
    }

    /*
     * Mostra la mappa di gioco.
     */
    public void mostraMappaGioco() {
        DatiAperturaMappa datiAperturaMappa =
                dati.aperturaMappa.creaDatiAperturaMappa(
                        dati.stage,
                        dati.creatoreElementiBaseMappa,
                        dati.controllerMappa,
                        dati.gestoreNemiciMappa,
                        dati.mappe[dati.indiceMappaAttuale],
                        mostraMenu,
                        this::mostraMappaPrecedente,
                        this::mostraMappaSuccessiva
                );

        Pane mappaPane = datiAperturaMappa.getMappaPane();
        dati.mappaPaneAttuale = mappaPane;

        DatiPreparazioneCombattimentoMappa datiPreparazione =
                dati.aperturaMappa.preparaCombattimentoMappa(
                        dati.gestoreInterfacciaMappa,
                        dati.gestorePulsantiMappa,
                        dati.dado,
                        gestoreCombattimento::combattiTurnoSuMappa,
                        gestoreCombattimento::eseguiAttaccoSquadraSuMappa,
                        gestoreCombattimento::potenziaVincitoreSuMappa
                );

        applicaDatiPreparazioneCombattimentoMappa(datiPreparazione);

        dati.aperturaMappa.aggiungiElementiBaseAllaMappa(
                dati.gestoreElementiMappa,
                datiAperturaMappa,
                dati.logMappa,
                dati.combattiMappaButton,
                dati.attaccoSquadraMappaButton,
                dati.potenziaMappaButton
        );
        dati.aperturaMappa.aggiungiPersonaggiAllaMappa(
                dati.controllerMappa,
                mappaPane,
                () -> aggiungiEroiAllaMappa(mappaPane),
                () -> aggiungiNemiciAllaMappa(mappaPane)
        );
        dati.gestoreIncontriMappa =
                dati.controllerMappa.creaGestoreIncontriMappa(
                        dati.gestoreInizializzazioneMappa
                );

        gestoreCombattimento.avviaMovimentoNemiciSuMappa(dati.stage);
        dati.controllerMappa.mostraSceneMappa(
                dati.stage,
                mappaPane,
                dati.controllerMappa.creaSceneMappa(
                        mappaPane,
                        () -> dati.eroeMappa,
                        () -> dati.logMappa,
                        dati.gestoreMovimentoEroe,
                        () -> gestoreCombattimento
                                .controllaCollisioneNemiciSuMappa(dati.stage)
                )
        );
    }

    /*
     * Mostra la mappa precedente.
     */
    private void mostraMappaPrecedente() {
        dati.controllerMappa.fermaMovimentoNemici(dati.gestoreNemiciMappa);
        dati.indiceMappaAttuale--;

        if (dati.indiceMappaAttuale < 0) {
            dati.indiceMappaAttuale = dati.mappe.length - 1;
        }

        mostraMappaGioco();
    }

    /*
     * Mostra la mappa successiva.
     */
    private void mostraMappaSuccessiva() {
        dati.controllerMappa.fermaMovimentoNemici(dati.gestoreNemiciMappa);
        dati.indiceMappaAttuale++;

        if (dati.indiceMappaAttuale >= dati.mappe.length) {
            dati.indiceMappaAttuale = 0;
        }

        mostraMappaGioco();
    }

    /*
     * Aggiunge i nemici alla mappa.
     */
    private void aggiungiNemiciAllaMappa(Pane mappaPane) {
        if (dati.nemiciDisponibili == null) {
            dati.nemiciDisponibili = dati.creatorePersonaggi.creaNemici();
        }

        dati.controllerMappa.aggiungiNemiciAllaMappa(
                mappaPane,
                dati.gestoreNemiciVisualiMappa,
                dati.nemiciDisponibili,
                dati.nemiciMappa,
                dati.nomiNemiciMappa,
                dati.nemiciAssociatiMappa,
                nemico -> gestoreCombattimento.avviaIncontroDaMappa(
                        nemico,
                        dati.stage
                )
        );
    }

    /*
     * Aggiunge gli eroi alla mappa.
     */
    private void aggiungiEroiAllaMappa(Pane mappaPane) {
        if (dati.eroiDisponibili == null) {
            dati.eroiDisponibili = dati.creatorePersonaggi.creaEroi();
        }

        dati.controllerMappa.aggiungiEroiAllaMappa(
                mappaPane,
                dati.gestoreEroiMappa,
                dati.eroiDisponibili,
                dati.eroiMappa,
                dati.eroiAssociatiMappa,
                (eroe, eroeView) -> {
                    DatiSelezioneEroeApplicazione datiSelezione =
                            dati.aperturaMappa.selezionaEroeDaMappa(
                                    dati.controllerMappa,
                                    eroe,
                                    eroeView,
                                    dati.gestoreMovimentoEroe,
                                    dati.eroiMappa,
                                    dati.logMappa
                            );

                    applicaSelezioneEroeMappa(datiSelezione);
                }
        );
    }

    /*
     * Applica la selezione dell'eroe sulla mappa.
     */
    private void applicaSelezioneEroeMappa(
            DatiSelezioneEroeApplicazione datiSelezione
    ) {
        dati.eroeAttuale = datiSelezione.getEroe();
        dati.eroeMappa = datiSelezione.getEroeView();

        dati.nomeGiocatoreAttuale = datiSelezione.getNome();
        dati.vitaGiocatoreAttuale = datiSelezione.getVita();
        dati.vitaGiocatoreMassima = datiSelezione.getVitaMassima();
        dati.dannoGiocatore = datiSelezione.getDanno();
        dati.eroeSelezionato = true;
    }

    /*
     * Applica i dati di preparazione del combattimento sulla mappa.
     */
    private void applicaDatiPreparazioneCombattimentoMappa(
            DatiPreparazioneCombattimentoMappa datiPreparazione
    ) {
        dati.logMappa = datiPreparazione.getLogMappa();
        dati.gestorePotenziamentiMappa =
                datiPreparazione.getGestorePotenziamentiMappa();
        dati.gestoreCombattimentoMappa =
                datiPreparazione.getGestoreCombattimentoMappa();
        dati.combattiMappaButton =
                datiPreparazione.getCombattiMappaButton();
        dati.attaccoSquadraMappaButton =
                datiPreparazione.getAttaccoSquadraMappaButton();
        dati.potenziaMappaButton =
                datiPreparazione.getPotenziaMappaButton();
    }
}
