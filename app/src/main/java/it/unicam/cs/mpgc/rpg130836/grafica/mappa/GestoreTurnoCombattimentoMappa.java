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
    public Combattimento creaCombattimentoMappa(Dado dado, TextArea logMappa) {
        return new Combattimento(
                dado,
                new TextAreaOutput(logMappa)
        );
    }
    public void terminaCombattimento(DatiCombattimentoMappa datiCombattimentoMappa) {
        datiCombattimentoMappa.setCombattimentoAttivo(false);
    }
    public boolean nemicoSconfitto(Nemico nemicoAttuale) {
        return nemicoAttuale != null && !nemicoAttuale.isVivo();
    }
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
    public void impostaAttaccoSquadraUsato(DatiCombattimentoMappa datiCombattimentoMappa) {
        datiCombattimentoMappa.setAttaccoSquadraUsato(true);
    }
    public void scriviMessaggioAttaccoSquadra(TextArea logMappa) {
        logMappa.appendText("ATTACCO DI SQUADRA!\n");
    }
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
    public void scriviViteAggiornate(Eroe eroeAttuale,
                                     Nemico nemicoAttuale,
                                     TextArea logMappa) {

        scriviViteDopoTurno(
                eroeAttuale,
                nemicoAttuale,
                logMappa
        );
    }
    public boolean logMappaPresente(TextArea logMappa) {
        return logMappa != null;
    }
    public void disabilitaPulsantePotenzia(Button potenziaButton) {
        if (potenziaButton != null) {
            potenziaButton.setDisable(true);
        }
    }

    public void resettaFocusMappa(Pane mappaPane) {
        if (mappaPane != null) {
            Platform.runLater(() -> mappaPane.requestFocus());
        }
    }
    public void resettaStatoDopoPotenziamento(DatiCombattimentoMappa datiCombattimentoMappa) {
        datiCombattimentoMappa.setPotenziamentoDisponibile(false);
        datiCombattimentoMappa.setPotenziamentoDiSquadra(false);
        datiCombattimentoMappa.setEroeDaPotenziare(null);
        datiCombattimentoMappa.setCombattimentoAttivo(false);
    }
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
    public boolean nemicoNonSconfitto(Nemico nemicoAttuale) {
        return nemicoAttuale == null || nemicoAttuale.isVivo();
    }
    public void scriviMessaggioNemicoSconfitto(Nemico nemicoAttuale,
                                               TextArea logMappa) {
        if (logMappa != null) {
            logMappa.appendText(nemicoAttuale.getNome() + " è stato sconfitto!\n");
        }
    }
    public void abilitaPotenziamentoDopoVittoria(DatiCombattimentoMappa datiCombattimentoMappa) {
        datiCombattimentoMappa.setPotenziamentoDisponibile(true);
    }
    public void abilitaPulsantePotenzia(Button potenziaButton) {
        if (potenziaButton != null) {
            potenziaButton.setDisable(false);
        }
    }
    public void impostaPotenziamentoDiSquadra(DatiCombattimentoMappa datiCombattimentoMappa,
                                              boolean valore) {
        datiCombattimentoMappa.setPotenziamentoDiSquadra(valore);
    }
    public void impostaEroeDaPotenziare(DatiCombattimentoMappa datiCombattimentoMappa,
                                        Eroe eroe) {
        datiCombattimentoMappa.setEroeDaPotenziare(eroe);
    }
    public void scriviMessaggioPotenziamentoSquadra(TextArea logMappa) {
        if (logMappa != null) {
            logMappa.appendText(
                    "Attacco di squadra riuscito! Premi Potenzia vincitore per potenziare tutti gli eroi.\n"
            );
        }
    }

    public void scriviMessaggioPotenziamentoEroe(Eroe eroeDaPotenziare,
                                                 TextArea logMappa) {
        if (logMappa != null && eroeDaPotenziare != null) {
            logMappa.appendText(
                    eroeDaPotenziare.getNome()
                            + " può essere potenziato! Premi Potenzia vincitore.\n"
            );
        }
    }
    public void disabilitaPulsantiCombattimento(Button combattiButton,
                                                Button attaccoSquadraButton) {
        if (combattiButton != null) {
            combattiButton.setDisable(true);
        }

        if (attaccoSquadraButton != null) {
            attaccoSquadraButton.setDisable(true);
        }
    }
    public void resettaCombattimentoDopoVittoria(DatiCombattimentoMappa datiCombattimentoMappa) {
        datiCombattimentoMappa.setCombattimentoAttivo(false);
    }
    public void resettaFocusMappaDopoVittoria(Pane mappaPane) {
        resettaFocusMappa(mappaPane);
    }
    public void disabilitaPulsanteAttaccoSquadra(Button attaccoSquadraButton) {
        if (attaccoSquadraButton != null) {
            attaccoSquadraButton.setDisable(true);
        }
    }
    public void impostaPotenziamentoDiSquadra(DatiCombattimentoMappa datiCombattimentoMappa) {
        datiCombattimentoMappa.setPotenziamentoDiSquadra(true);
    }

}