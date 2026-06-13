package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import it.unicam.cs.mpgc.rpg130836.model.combattimento.Combattimento;
import it.unicam.cs.mpgc.rpg130836.model.combattimento.Dado;
import it.unicam.cs.mpgc.rpg130836.model.combattimento.DatiCombattimentoMappa;
import it.unicam.cs.mpgc.rpg130836.model.combattimento.DatiIncontroMappa;
import it.unicam.cs.mpgc.rpg130836.model.combattimento.DatiTurnoMappa;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class ControllerCombattimentoMappa {

    private final GestoreAvvioIncontroMappa gestoreAvvioIncontroMappa;
    private final GestorePulsantiMappa gestorePulsantiMappa;

    public ControllerCombattimentoMappa(
            GestoreAvvioIncontroMappa gestoreAvvioIncontroMappa,
            GestorePulsantiMappa gestorePulsantiMappa
    ) {
        this.gestoreAvvioIncontroMappa = gestoreAvvioIncontroMappa;
        this.gestorePulsantiMappa = gestorePulsantiMappa;
    }

    public boolean possoAvviareIncontro(
            Nemico nemico,
            boolean potenziamentoDisponibile,
            TextArea logMappa,
            GestoreIncontriMappa gestoreIncontriMappa
    ) {
        return gestoreAvvioIncontroMappa.possoAvviareIncontro(
                nemico,
                potenziamentoDisponibile,
                logMappa,
                gestoreIncontriMappa
        );
    }

    public void scriviLogIncontro(
            TextArea logMappa,
            String nomeNemicoAttuale,
            String nomeGiocatoreAttuale,
            int vitaGiocatoreAttuale,
            int vitaGiocatoreMassima,
            int vitaNemicoAttuale,
            int vitaNemicoMassima
    ) {
        gestoreAvvioIncontroMappa.scriviLogIncontro(
                logMappa,
                nomeNemicoAttuale,
                nomeGiocatoreAttuale,
                vitaGiocatoreAttuale,
                vitaGiocatoreMassima,
                vitaNemicoAttuale,
                vitaNemicoMassima
        );
    }

    public void abilitaPulsantiCombattimentoMappa(
            Button combattiMappaButton,
            Button attaccoSquadraMappaButton,
            Button potenziaMappaButton
    ) {
        gestorePulsantiMappa.abilitaPulsantiCombattimentoMappa(
                combattiMappaButton,
                attaccoSquadraMappaButton,
                potenziaMappaButton
        );
    }
    public DatiIncontroMappa preparaIncontroMappa(
            Nemico nemico,
            Eroe eroeAttuale,
            DatiCombattimentoMappa datiCombattimentoMappa,
            GestoreIncontriMappa gestoreIncontriMappa
    ) {
        gestoreAvvioIncontroMappa.impostaCombattimentoAttivo(datiCombattimentoMappa);

        gestoreAvvioIncontroMappa.inizializzaDatiNemicoMappa(
                datiCombattimentoMappa,
                nemico
        );

        String nomeNemicoAttuale =
                gestoreAvvioIncontroMappa.prendiNomeNemico(
                        gestoreIncontriMappa,
                        nemico
                );

        int vitaNemicoAttuale =
                gestoreAvvioIncontroMappa.prendiVitaNemico(
                        gestoreIncontriMappa,
                        nemico
                );

        int vitaNemicoMassima =
                gestoreAvvioIncontroMappa.prendiVitaMassimaNemico(
                        gestoreIncontriMappa,
                        nemico
                );

        String nomeGiocatoreAttuale = "Nessun eroe";
        int vitaGiocatoreAttuale = 100;
        int vitaGiocatoreMassima = 100;
        int dannoGiocatore = 0;
        boolean eroeSelezionato = false;

        if (gestoreAvvioIncontroMappa.eroePresente(eroeAttuale)) {
            gestoreAvvioIncontroMappa.inizializzaDatiEroeMappa(
                    datiCombattimentoMappa,
                    eroeAttuale
            );

            nomeGiocatoreAttuale =
                    gestoreAvvioIncontroMappa.prendiNomeEroe(eroeAttuale);
            vitaGiocatoreAttuale =
                    gestoreAvvioIncontroMappa.prendiVitaEroe(eroeAttuale);
            vitaGiocatoreMassima =
                    gestoreAvvioIncontroMappa.prendiVitaMassimaEroe(eroeAttuale);
            dannoGiocatore =
                    gestoreAvvioIncontroMappa.prendiDannoEroe(eroeAttuale);

            eroeSelezionato = true;
        }

        gestoreAvvioIncontroMappa.resettaStatoCombattimentoMappa(
                datiCombattimentoMappa
        );

        gestoreAvvioIncontroMappa.resettaVariabiliCombattimentoMappa(
                datiCombattimentoMappa
        );

        return new DatiIncontroMappa(
                nemico,
                nomeNemicoAttuale,
                vitaNemicoAttuale,
                vitaNemicoMassima,
                nomeGiocatoreAttuale,
                vitaGiocatoreAttuale,
                vitaGiocatoreMassima,
                dannoGiocatore,
                eroeSelezionato
        );
    }
    public DatiTurnoMappa combattiTurnoSuMappa(
            GestoreTurnoCombattimentoMappa gestoreTurnoCombattimentoMappa,
            Dado dado,
            Eroe eroeAttuale,
            boolean eroeSelezionato,
            Nemico nemicoAttuale,
            TextArea logMappa,
            DatiCombattimentoMappa datiCombattimentoMappa,
            Pane mappaPaneAttuale,
            Runnable animazioneAttacco
    ) {
        if (!gestoreTurnoCombattimentoMappa.puoCombattere(
                eroeAttuale,
                eroeSelezionato,
                nemicoAttuale,
                logMappa
        )) {
            return null;
        }

        animazioneAttacco.run();

        Combattimento combattimentoMappa =
                gestoreTurnoCombattimentoMappa.creaCombattimentoMappa(
                        dado,
                        logMappa
                );

        combattimentoMappa.eseguiTurnoSingolo(
                eroeAttuale,
                nemicoAttuale
        );

        gestoreTurnoCombattimentoMappa.scriviViteDopoTurno(
                eroeAttuale,
                nemicoAttuale,
                logMappa
        );

        gestoreTurnoCombattimentoMappa.terminaCombattimento(
                datiCombattimentoMappa
        );

        gestoreTurnoCombattimentoMappa.resettaFocusMappa(
                mappaPaneAttuale
        );

        return new DatiTurnoMappa(
                eroeAttuale.getVita(),
                eroeAttuale.getVitaMassima(),
                nemicoAttuale.getVita(),
                nemicoAttuale.getVitaMassima(),
                gestoreTurnoCombattimentoMappa.nemicoSconfitto(nemicoAttuale)
        );
    }
}