package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import it.unicam.cs.mpgc.rpg130836.model.combattimento.DatiCombattimentoMappa;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import javafx.scene.control.TextArea;

public class GestoreAvvioIncontroMappa {

    public boolean possoAvviareIncontro(Nemico nemico,
                                        boolean potenziamentoDisponibile,
                                        TextArea logMappa,
                                        GestoreIncontriMappa gestoreIncontriMappa) {

        if (!gestoreIncontriMappa.incontroValido(nemico)) {
            return false;
        }

        if (potenziamentoDisponibile) {
            if (logMappa != null) {
                logMappa.appendText("Prima devi usare il potenziamento disponibile.\n");
            }
            return false;
        }

        return true;
    }

    public void scriviLogIncontro(TextArea logMappa,
                                  String nomeNemicoAttuale,
                                  String nomeGiocatoreAttuale,
                                  int vitaGiocatoreAttuale,
                                  int vitaGiocatoreMassima,
                                  int vitaNemicoAttuale,
                                  int vitaNemicoMassima) {

        if (logMappa == null) {
            return;
        }

        logMappa.appendText("Hai incontrato " + nomeNemicoAttuale + " sulla mappa!\n");
        logMappa.appendText("Eroe: " + nomeGiocatoreAttuale + " | Vita: "
                + vitaGiocatoreAttuale + "/" + vitaGiocatoreMassima + "\n");
        logMappa.appendText("Nemico: " + nomeNemicoAttuale + " | Vita: "
                + vitaNemicoAttuale + "/" + vitaNemicoMassima + "\n");
    }
    public void resettaStatoCombattimentoMappa(DatiCombattimentoMappa datiCombattimentoMappa) {
        datiCombattimentoMappa.setAttaccoSquadraUsato(false);
        datiCombattimentoMappa.setPotenziamentoDisponibile(false);
        datiCombattimentoMappa.setEroeDaPotenziare(null);
        datiCombattimentoMappa.setPotenziamentoDiSquadra(false);
    }
    public void inizializzaDatiNemicoMappa(DatiCombattimentoMappa datiCombattimentoMappa,
                                           Nemico nemico) {
        datiCombattimentoMappa.setNemicoAttuale(nemico);
    }
    public void inizializzaDatiEroeMappa(DatiCombattimentoMappa datiCombattimentoMappa,
                                         Eroe eroeAttuale) {
        if (eroeAttuale == null) {
            return;
        }

        datiCombattimentoMappa.setEroeAttuale(eroeAttuale);
        datiCombattimentoMappa.setEroeSelezionato(true);
    }
    public void impostaCombattimentoAttivo(DatiCombattimentoMappa datiCombattimentoMappa) {
        datiCombattimentoMappa.setCombattimentoAttivo(true);
    }
    public String prendiNomeNemico(GestoreIncontriMappa gestoreIncontriMappa,
                                   Nemico nemico) {
        return gestoreIncontriMappa.prendiNomeNemico(nemico);
    }

    public int prendiVitaNemico(GestoreIncontriMappa gestoreIncontriMappa,
                                Nemico nemico) {
        return gestoreIncontriMappa.prendiVitaNemico(nemico);
    }

    public int prendiVitaMassimaNemico(GestoreIncontriMappa gestoreIncontriMappa,
                                       Nemico nemico) {
        return gestoreIncontriMappa.prendiVitaMassimaNemico(nemico);
    }
    public boolean eroePresente(Eroe eroeAttuale) {
        return eroeAttuale != null;
    }

    public String prendiNomeEroe(Eroe eroeAttuale) {
        return eroeAttuale.getNome();
    }

    public int prendiVitaEroe(Eroe eroeAttuale) {
        return eroeAttuale.getVita();
    }

    public int prendiVitaMassimaEroe(Eroe eroeAttuale) {
        return eroeAttuale.getVitaMassima();
    }

    public int prendiDannoEroe(Eroe eroeAttuale) {
        return eroeAttuale.calcolaAttacco();
    }
    public void resettaStatoCombattimentoMappaCompleto(DatiCombattimentoMappa datiCombattimentoMappa) {
        resettaStatoCombattimentoMappa(datiCombattimentoMappa);
    }
    public void resettaVariabiliCombattimentoMappa(DatiCombattimentoMappa datiCombattimentoMappa) {
        resettaStatoCombattimentoMappa(datiCombattimentoMappa);
    }
}