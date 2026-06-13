package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import it.unicam.cs.mpgc.rpg130836.grafica.gioco.TextAreaOutput;
import it.unicam.cs.mpgc.rpg130836.model.abilita.GestorePotenziamenti;
import it.unicam.cs.mpgc.rpg130836.model.combattimento.Combattimento;
import it.unicam.cs.mpgc.rpg130836.model.combattimento.Dado;
import it.unicam.cs.mpgc.rpg130836.model.combattimento.DatiCombattimentoMappa;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import java.util.function.Consumer;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class GestoreTurnoCombattimentoMappa {

    /*
     * Controlla se è possibile combattere.
     */
    public boolean puoCombattere(Eroe eroeAttuale,
                                 boolean eroeSelezionato,
                                 Nemico nemicoAttuale,
                                 TextArea logMappa) {

        if (eroeAttuale == null || !eroeSelezionato) {
            logMappa.appendText("Devi prima scegliere un eroe.\n");
            return false;
        }

        if (nemicoAttuale == null || !nemicoAttuale.isVivo()) {
            logMappa.appendText("Devi prima toccare un nemico sulla mappa.\n");
            return false;
        }

        if (!eroeAttuale.isVivo()) {
            logMappa.appendText(eroeAttuale.getNome() + " è sconfitto e non può combattere.\n");
            return false;
        }

        return true;
    }

    /*
     * Scrive nel log le vite dopo il turno.
     */
    public void scriviViteDopoTurno(Eroe eroeAttuale,
                                    Nemico nemicoAttuale,
                                    TextArea logMappa) {

        logMappa.appendText(
                eroeAttuale.getNome() + " vita: "
                        + eroeAttuale.getVita() + "/" + eroeAttuale.getVitaMassima() + "\n"
        );

        logMappa.appendText(
                nemicoAttuale.getNome() + " vita: "
                        + nemicoAttuale.getVita() + "/" + nemicoAttuale.getVitaMassima() + "\n"
        );
    }

    /*
     * Crea il combattimento della mappa.
     */
    public Combattimento creaCombattimentoMappa(Dado dado, TextArea logMappa) {
        return new Combattimento(
                dado,
                new TextAreaOutput(logMappa)
        );
    }

    /*
     * Termina il combattimento corrente.
     */
    public void terminaCombattimento(DatiCombattimentoMappa datiCombattimentoMappa) {
        datiCombattimentoMappa.setCombattimentoAttivo(false);
    }

    /*
     * Controlla se il nemico è stato sconfitto.
     */
    public boolean nemicoSconfitto(Nemico nemicoAttuale) {
        return nemicoAttuale != null && !nemicoAttuale.isVivo();
    }

    /*
     * Controlla se è possibile usare l'attacco di squadra sulla mappa.
     */
    public boolean puoUsareAttaccoSquadraMappa(boolean attaccoSquadraUsato,
                                               Eroe eroeAttuale,
                                               boolean eroeSelezionato,
                                               Nemico nemicoAttuale,
                                               TextArea logMappa) {

        if (attaccoSquadraUsato) {
            logMappa.appendText("Hai già usato l'attacco di squadra contro questo nemico.\n");
            return false;
        }

        if (logMappa == null) {
            return false;
        }

        if (eroeAttuale == null || !eroeSelezionato) {
            logMappa.appendText("Devi prima selezionare un eroe sulla mappa.\n");
            return false;
        }

        if (nemicoAttuale == null || !nemicoAttuale.isVivo()) {
            logMappa.appendText("Devi prima selezionare un nemico vivo.\n");
            return false;
        }

        return true;
    }

    /*
     * Imposta l'attacco di squadra usato.
     */
    public void impostaAttaccoSquadraUsato(DatiCombattimentoMappa datiCombattimentoMappa) {
        datiCombattimentoMappa.setAttaccoSquadraUsato(true);
    }

    /*
     * Scrive il messaggio dell'attacco di squadra.
     */
    public void scriviMessaggioAttaccoSquadra(TextArea logMappa) {
        logMappa.appendText("ATTACCO DI SQUADRA!\n");
    }

    /*
     * Scrive il danno causato dall'attacco di squadra.
     */
    public void scriviDannoAttaccoSquadra(Eroe eroe,
                                          Nemico nemico,
                                          int danno,
                                          TextArea logMappa) {
        logMappa.appendText(
                eroe.getNome()
                        + " attacca "
                        + nemico.getNome()
                        + " causando "
                        + danno
                        + " danni.\n"
        );
    }

    /*
     * Esegue l'attacchi squadra.
     */
    public void eseguiAttacchiSquadra(List<Eroe> eroiDisponibili,
                                      Nemico nemicoAttuale,
                                      TextArea logMappa,
                                      Consumer<Eroe> animazioneAttaccoEroe) {

        for (Eroe eroe : eroiDisponibili) {
            if (eroe.isVivo() && nemicoAttuale.isVivo()) {

                animazioneAttaccoEroe.accept(eroe);

                int danno = eroe.attacca(nemicoAttuale);

                scriviDannoAttaccoSquadra(
                        eroe,
                        nemicoAttuale,
                        danno,
                        logMappa
                );
            }
        }
    }

    /*
     * Scrive nel log le vite aggiornate.
     */
    public void scriviViteAggiornate(Eroe eroeAttuale,
                                     Nemico nemicoAttuale,
                                     TextArea logMappa) {

        scriviViteDopoTurno(
                eroeAttuale,
                nemicoAttuale,
                logMappa
        );
    }

    /*
     * Controlla se il log della mappa è presente.
     */
    public boolean logMappaPresente(TextArea logMappa) {
        return logMappa != null;
    }

    /*
     * Disabilita il pulsante di potenziamento.
     */
    public void disabilitaPulsantePotenzia(Button potenziaButton) {
        if (potenziaButton != null) {
            potenziaButton.setDisable(true);
        }
    }

    /*
     * Resetta il focus della mappa.
     */
    public void resettaFocusMappa(Pane mappaPane) {
        if (mappaPane != null) {
            Platform.runLater(() -> mappaPane.requestFocus());
        }
    }

    /*
     * Resetta lo stato dopo il potenziamento.
     */
    public void resettaStatoDopoPotenziamento(DatiCombattimentoMappa datiCombattimentoMappa) {
        datiCombattimentoMappa.setPotenziamentoDisponibile(false);
        datiCombattimentoMappa.setPotenziamentoDiSquadra(false);
        datiCombattimentoMappa.setEroeDaPotenziare(null);
        datiCombattimentoMappa.setCombattimentoAttivo(false);
    }

    /*
     * Esegue il potenziamento mappa.
     */
    public boolean eseguiPotenziamentoMappa(boolean potenziamentoDiSquadra,
                                            List<Eroe> eroiDisponibili,
                                            Eroe eroeDaPotenziare,
                                            GestoreCombattimentoMappa gestoreCombattimentoMappa,
                                            GestorePotenziamenti gestorePotenziamentiMappa,
                                            TextArea logMappa) {

        if (potenziamentoDiSquadra) {
            gestoreCombattimentoMappa.potenziaSquadra(
                    eroiDisponibili,
                    gestorePotenziamentiMappa
            );
            return true;
        }

        if (!gestoreCombattimentoMappa.puoPotenziareEroe(
                eroeDaPotenziare,
                logMappa
        )) {
            return false;
        }

        gestorePotenziamentiMappa.potenziaVincitore(eroeDaPotenziare);
        return true;
    }

    /*
     * Controlla se il nemico non è stato sconfitto.
     */
    public boolean nemicoNonSconfitto(Nemico nemicoAttuale) {
        return nemicoAttuale == null || nemicoAttuale.isVivo();
    }

    /*
     * Scrive il messaggio del nemico sconfitto.
     */
    public void scriviMessaggioNemicoSconfitto(Nemico nemicoAttuale,
                                               TextArea logMappa) {
        if (logMappa != null) {
            logMappa.appendText(nemicoAttuale.getNome() + " è stato sconfitto!\n");
        }
    }

    /*
     * Abilita il potenziamento dopo la vittoria.
     */
    public void abilitaPotenziamentoDopoVittoria(DatiCombattimentoMappa datiCombattimentoMappa) {
        datiCombattimentoMappa.setPotenziamentoDisponibile(true);
    }

    /*
     * Abilita il pulsante di potenziamento.
     */
    public void abilitaPulsantePotenzia(Button potenziaButton) {
        if (potenziaButton != null) {
            potenziaButton.setDisable(false);
        }
    }

    /*
     * Imposta il potenziamento di squadra.
     */
    public void impostaPotenziamentoDiSquadra(DatiCombattimentoMappa datiCombattimentoMappa,
                                              boolean valore) {
        datiCombattimentoMappa.setPotenziamentoDiSquadra(valore);
    }

    /*
     * Imposta l'eroe da potenziare.
     */
    public void impostaEroeDaPotenziare(DatiCombattimentoMappa datiCombattimentoMappa,
                                        Eroe eroe) {
        datiCombattimentoMappa.setEroeDaPotenziare(eroe);
    }

    /*
     * Scrive il messaggio del potenziamento di squadra.
     */
    public void scriviMessaggioPotenziamentoSquadra(TextArea logMappa) {
        if (logMappa != null) {
            logMappa.appendText(
                    "Attacco di squadra riuscito! Premi Potenzia vincitore per potenziare tutti gli eroi.\n"
            );
        }
    }

    /*
     * Scrive il messaggio del potenziamento dell'eroe.
     */
    public void scriviMessaggioPotenziamentoEroe(Eroe eroeDaPotenziare,
                                                 TextArea logMappa) {
        if (logMappa != null && eroeDaPotenziare != null) {
            logMappa.appendText(
                    eroeDaPotenziare.getNome()
                            + " può essere potenziato! Premi Potenzia vincitore.\n"
            );
        }
    }

    /*
     * Disabilita i pulsanti combattimento.
     */
    public void disabilitaPulsantiCombattimento(Button combattiButton,
                                                Button attaccoSquadraButton) {
        if (combattiButton != null) {
            combattiButton.setDisable(true);
        }

        if (attaccoSquadraButton != null) {
            attaccoSquadraButton.setDisable(true);
        }
    }

    /*
     * Resetta il combattimento dopo la vittoria.
     */
    public void resettaCombattimentoDopoVittoria(DatiCombattimentoMappa datiCombattimentoMappa) {
        datiCombattimentoMappa.setCombattimentoAttivo(false);
    }

    /*
     * Resetta il focus della mappa dopo la vittoria.
     */
    public void resettaFocusMappaDopoVittoria(Pane mappaPane) {
        resettaFocusMappa(mappaPane);
    }

    /*
     * Disabilita il pulsante dell'attacco di squadra.
     */
    public void disabilitaPulsanteAttaccoSquadra(Button attaccoSquadraButton) {
        if (attaccoSquadraButton != null) {
            attaccoSquadraButton.setDisable(true);
        }
    }

    /*
     * Imposta il potenziamento di squadra.
     */
    public void impostaPotenziamentoDiSquadra(DatiCombattimentoMappa datiCombattimentoMappa) {
        datiCombattimentoMappa.setPotenziamentoDiSquadra(true);
    }

}