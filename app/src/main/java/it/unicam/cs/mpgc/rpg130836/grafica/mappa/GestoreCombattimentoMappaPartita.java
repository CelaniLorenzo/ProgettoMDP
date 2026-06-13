package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import it.unicam.cs.mpgc.rpg130836.grafica.Avvio.DatiApplicazioneRpg;
import it.unicam.cs.mpgc.rpg130836.model.combattimento.DatiIncontroMappa;
import it.unicam.cs.mpgc.rpg130836.model.combattimento.DatiTurnoMappa;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import java.util.Objects;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GestoreCombattimentoMappaPartita {

    private final DatiApplicazioneRpg dati;

    /*
     * Costruisce un'istanza di GestoreCombattimentoMappaPartita.
     */
    public GestoreCombattimentoMappaPartita(DatiApplicazioneRpg dati) {
        this.dati = Objects.requireNonNull(dati);
    }

    /*
     * Avvia un incontro dalla mappa.
     */
    public void avviaIncontroDaMappa(Nemico nemico, Stage stage) {
        if (!dati.controllerCombattimentoMappa.possoAvviareIncontro(
                nemico,
                dati.potenziamentoDisponibile,
                dati.logMappa,
                dati.gestoreIncontriMappa
        )) {
            return;
        }

        DatiIncontroMappa datiIncontro =
                dati.controllerCombattimentoMappa.preparaIncontroMappa(
                        nemico,
                        dati.eroeAttuale,
                        dati.datiCombattimentoMappa,
                        dati.gestoreIncontriMappa
                );

        applicaDatiIncontroMappa(datiIncontro);
        dati.statoPartitaCorrente.setNemicoAttuale(dati.nemicoAttuale);

        dati.controllerCombattimentoMappa.scriviLogIncontro(
                dati.logMappa,
                dati.nomeNemicoAttuale,
                dati.nomeGiocatoreAttuale,
                dati.vitaGiocatoreAttuale,
                dati.vitaGiocatoreMassima,
                dati.vitaNemicoAttuale,
                dati.vitaNemicoMassima
        );
        dati.controllerCombattimentoMappa.abilitaPulsantiCombattimentoMappa(
                dati.combattiMappaButton,
                dati.attaccoSquadraMappaButton,
                dati.potenziaMappaButton
        );
    }

    /*
     * Esegue un turno di combattimento sulla mappa.
     */
    public void combattiTurnoSuMappa() {
        DatiTurnoMappa datiTurno =
                dati.controllerCombattimentoMappa.combattiTurnoSuMappa(
                        dati.gestoreTurnoCombattimentoMappa,
                        dati.dado,
                        dati.eroeAttuale,
                        dati.eroeSelezionato,
                        dati.nemicoAttuale,
                        dati.logMappa,
                        dati.datiCombattimentoMappa,
                        dati.mappaPaneAttuale,
                        this::lanciaPotereVersoNemico
                );

        if (datiTurno == null) {
            return;
        }

        dati.vitaGiocatoreAttuale = datiTurno.getVitaGiocatoreAttuale();
        dati.vitaGiocatoreMassima = datiTurno.getVitaGiocatoreMassima();
        dati.vitaNemicoAttuale = datiTurno.getVitaNemicoAttuale();
        dati.vitaNemicoMassima = datiTurno.getVitaNemicoMassima();
        dati.combattimentoMappaAttivo = false;

        if (datiTurno.isNemicoSconfitto()) {
            gestisciNemicoSconfittoSuMappa();
        }
    }

    /*
     * Esegue l'attacco squadra su mappa.
     */
    public void eseguiAttaccoSquadraSuMappa() {
        if (!dati.gestoreTurnoCombattimentoMappa.puoUsareAttaccoSquadraMappa(
                dati.attaccoSquadraUsato,
                dati.eroeAttuale,
                dati.eroeSelezionato,
                dati.nemicoAttuale,
                dati.logMappa
        )) {
            return;
        }

        dati.attaccoSquadraUsato = true;
        dati.gestoreTurnoCombattimentoMappa.impostaAttaccoSquadraUsato(
                dati.datiCombattimentoMappa
        );
        dati.gestoreTurnoCombattimentoMappa.disabilitaPulsanteAttaccoSquadra(
                dati.attaccoSquadraMappaButton
        );
        dati.gestoreTurnoCombattimentoMappa.resettaFocusMappa(
                dati.mappaPaneAttuale
        );

        dati.gestoreTurnoCombattimentoMappa.scriviMessaggioAttaccoSquadra(
                dati.logMappa
        );
        dati.gestoreTurnoCombattimentoMappa.eseguiAttacchiSquadra(
                dati.eroiDisponibili,
                dati.nemicoAttuale,
                dati.logMappa,
                this::lanciaPotereEroeVersoNemico
        );

        dati.vitaGiocatoreAttuale = dati.eroeAttuale.getVita();
        dati.vitaGiocatoreMassima = dati.eroeAttuale.getVitaMassima();
        dati.vitaNemicoAttuale = dati.nemicoAttuale.getVita();
        dati.vitaNemicoMassima = dati.nemicoAttuale.getVitaMassima();

        dati.gestoreTurnoCombattimentoMappa.scriviViteAggiornate(
                dati.eroeAttuale,
                dati.nemicoAttuale,
                dati.logMappa
        );

        if (dati.gestoreTurnoCombattimentoMappa.nemicoSconfitto(
                dati.nemicoAttuale
        )) {
            dati.gestoreTurnoCombattimentoMappa.impostaPotenziamentoDiSquadra(
                    dati.datiCombattimentoMappa
            );
            dati.potenziamentoDiSquadra = true;
            gestisciNemicoSconfittoSuMappa();
        }
    }

    /*
     * Potenzia il vincitore sulla mappa.
     */
    public void potenziaVincitoreSuMappa() {
        if (!dati.gestoreTurnoCombattimentoMappa.logMappaPresente(
                dati.logMappa
        )) {
            return;
        }

        if (!dati.gestoreCombattimentoMappa.puoPotenziareMappa(
                dati.potenziamentoDisponibile,
                dati.logMappa
        )) {
            return;
        }

        if (!dati.gestoreTurnoCombattimentoMappa.eseguiPotenziamentoMappa(
                dati.potenziamentoDiSquadra,
                dati.eroiDisponibili,
                dati.eroeDaPotenziare,
                dati.gestoreCombattimentoMappa,
                dati.gestorePotenziamentiMappa,
                dati.logMappa
        )) {
            return;
        }

        dati.potenziamentoDisponibile = false;
        dati.potenziamentoDiSquadra = false;
        dati.eroeDaPotenziare = null;
        dati.gestoreTurnoCombattimentoMappa.resettaStatoDopoPotenziamento(
                dati.datiCombattimentoMappa
        );
        dati.gestoreTurnoCombattimentoMappa.disabilitaPulsantePotenzia(
                dati.potenziaMappaButton
        );
        dati.combattimentoMappaAttivo = false;
        dati.gestoreTurnoCombattimentoMappa.resettaFocusMappa(
                dati.mappaPaneAttuale
        );
    }

    /*
     * Avvia il movimento dei nemici sulla mappa.
     */
    public void avviaMovimentoNemiciSuMappa(Stage stage) {
        dati.gestoreMovimentoNemiciMappa =
                dati.controllerMappa.creaGestoreMovimentoNemiciMappa(
                        dati.gestoreInizializzazioneMappa,
                        dati.nemiciMappa,
                        dati.nemiciAssociatiMappa
                );

        dati.controllerMappa.avviaMovimentoNemici(
                dati.gestoreNemiciMappa,
                dati.gestoreMovimentoNemiciMappa,
                () -> controllaCollisioneNemiciSuMappa(stage)
        );
    }

    /*
     * Controlla il collisione nemici su mappa.
     */
    public void controllaCollisioneNemiciSuMappa(Stage stage) {
        dati.controllerMappa.controllaCollisioneNemici(
                dati.gestoreCollisioniMappa,
                dati.eroeMappa,
                dati.nemiciMappa,
                dati.nemiciAssociatiMappa,
                nemicoToccato ->
                        dati.controllerMappa.gestisciCollisioneConNemico(
                                nemicoToccato,
                                () -> avviaIncontroDaMappa(nemicoToccato, stage)
                        )
        );
    }

    /*
     * Applica i dati di incontro mappa.
     */
    private void applicaDatiIncontroMappa(DatiIncontroMappa datiIncontro) {
        dati.combattimentoMappaAttivo = true;
        dati.nemicoAttuale = datiIncontro.getNemicoAttuale();
        dati.statoPartitaCorrente.setNemicoAttuale(dati.nemicoAttuale);

        dati.nomeNemicoAttuale = datiIncontro.getNomeNemicoAttuale();
        dati.vitaNemicoAttuale = datiIncontro.getVitaNemicoAttuale();
        dati.vitaNemicoMassima = datiIncontro.getVitaNemicoMassima();
        dati.nomeGiocatoreAttuale = datiIncontro.getNomeGiocatoreAttuale();
        dati.vitaGiocatoreAttuale = datiIncontro.getVitaGiocatoreAttuale();
        dati.vitaGiocatoreMassima = datiIncontro.getVitaGiocatoreMassima();
        dati.dannoGiocatore = datiIncontro.getDannoGiocatore();
        dati.eroeSelezionato = datiIncontro.isEroeSelezionato();

        dati.attaccoSquadraUsato = false;
        dati.potenziamentoDisponibile = false;
        dati.statoPartitaCorrente.setPotenziamentoDisponibile(false);
        dati.eroeDaPotenziare = null;
        dati.potenziamentoDiSquadra = false;
    }

    /*
     * Gestisce la sconfitta del nemico sulla mappa.
     */
    private void gestisciNemicoSconfittoSuMappa() {
        if (dati.gestoreTurnoCombattimentoMappa.nemicoNonSconfitto(
                dati.nemicoAttuale
        )) {
            return;
        }

        dati.gestoreTurnoCombattimentoMappa.scriviMessaggioNemicoSconfitto(
                dati.nemicoAttuale,
                dati.logMappa
        );
        rimuoviNemicoSconfittoDallaMappa();
        preparaPotenziamentoDopoVittoria();

        dati.nemicoAttuale = null;
        dati.nomeNemicoAttuale = "";
        dati.vitaNemicoAttuale = 0;
        dati.vitaNemicoMassima = 0;

        dati.gestoreTurnoCombattimentoMappa.disabilitaPulsantiCombattimento(
                dati.combattiMappaButton,
                dati.attaccoSquadraMappaButton
        );
        dati.combattimentoMappaAttivo = false;
        dati.gestoreTurnoCombattimentoMappa.resettaCombattimentoDopoVittoria(
                dati.datiCombattimentoMappa
        );

        if (dati.mappaPaneAttuale != null) {
            Stage stage = (Stage) dati.mappaPaneAttuale.getScene().getWindow();
            avviaMovimentoNemiciSuMappa(stage);
            dati.gestoreTurnoCombattimentoMappa
                    .resettaFocusMappaDopoVittoria(dati.mappaPaneAttuale);
        }
    }

    /*
     * Prepara il potenziamento dopo la vittoria.
     */
    private void preparaPotenziamentoDopoVittoria() {
        dati.potenziamentoDisponibile = true;
        dati.gestoreTurnoCombattimentoMappa.abilitaPotenziamentoDopoVittoria(
                dati.datiCombattimentoMappa
        );

        if (dati.potenziamentoDiSquadra) {
            dati.eroeDaPotenziare = null;
            dati.gestoreTurnoCombattimentoMappa.impostaEroeDaPotenziare(
                    dati.datiCombattimentoMappa,
                    null
            );
            dati.gestoreTurnoCombattimentoMappa
                    .scriviMessaggioPotenziamentoSquadra(dati.logMappa);
        } else {
            dati.eroeDaPotenziare = dati.eroeAttuale;
            dati.gestoreTurnoCombattimentoMappa.impostaEroeDaPotenziare(
                    dati.datiCombattimentoMappa,
                    dati.eroeAttuale
            );
            dati.gestoreTurnoCombattimentoMappa.scriviMessaggioPotenziamentoEroe(
                    dati.eroeDaPotenziare,
                    dati.logMappa
            );
        }

        dati.gestoreTurnoCombattimentoMappa.abilitaPulsantePotenzia(
                dati.potenziaMappaButton
        );
    }

    /*
     * Rimuove il nemico sconfitto dalla mappa.
     */
    private void rimuoviNemicoSconfittoDallaMappa() {
        dati.gestoreRimozioneNemiciMappa.rimuoviNemicoSconfitto(
                dati.mappaPaneAttuale,
                dati.nemicoAttuale,
                dati.nemiciMappa,
                dati.nomiNemiciMappa,
                dati.nemiciAssociatiMappa
        );
    }

    /*
     * Lancia il potere verso il nemico.
     */
    private void lanciaPotereVersoNemico() {
        if (dati.mappaPaneAttuale == null
                || dati.eroeMappa == null
                || dati.nemicoAttuale == null) {
            return;
        }

        int indiceNemico = dati.nemiciAssociatiMappa.indexOf(dati.nemicoAttuale);

        if (indiceNemico == -1) {
            return;
        }

        ImageView nemicoView = dati.nemiciMappa.get(indiceNemico);

        dati.gestoreAnimazioni.lanciaPotereVersoNemicoCorrente(
                dati.mappaPaneAttuale,
                dati.eroeMappa,
                nemicoView,
                dati.risorseImmagini.percorsoPotere(dati.eroeAttuale)
        );
    }

    /*
     * Lancia il potere dell'eroe verso il nemico.
     */
    private void lanciaPotereEroeVersoNemico(Eroe eroe) {
        if (dati.mappaPaneAttuale == null || dati.nemicoAttuale == null) {
            return;
        }

        int indiceEroe = dati.eroiAssociatiMappa.indexOf(eroe);
        int indiceNemico = dati.nemiciAssociatiMappa.indexOf(dati.nemicoAttuale);

        if (indiceEroe == -1 || indiceNemico == -1) {
            return;
        }

        ImageView eroeView = dati.eroiMappa.get(indiceEroe);
        ImageView nemicoView = dati.nemiciMappa.get(indiceNemico);

        dati.gestoreAnimazioni.lanciaPotere(
                dati.mappaPaneAttuale,
                eroeView,
                nemicoView,
                dati.risorseImmagini.percorsoPotere(eroe)
        );
    }
}
