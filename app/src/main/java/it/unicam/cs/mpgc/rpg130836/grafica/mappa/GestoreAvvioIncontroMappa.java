package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import it.unicam.cs.mpgc.rpg130836.model.combattimento.DatiCombattimentoMappa;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import javafx.scene.control.TextArea;

public class GestoreAvvioIncontroMappa {

    /*
     * Controlla se è possibile avviare un incontro.
     */
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

    /*
     * Scrive nel log il messaggio dell'incontro.
     */
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

    /*
     * Resetta lo stato del combattimento sulla mappa.
     */
    public void resettaStatoCombattimentoMappa(DatiCombattimentoMappa datiCombattimentoMappa) {
        datiCombattimentoMappa.setAttaccoSquadraUsato(false);
        datiCombattimentoMappa.setPotenziamentoDisponibile(false);
        datiCombattimentoMappa.setEroeDaPotenziare(null);
        datiCombattimentoMappa.setPotenziamentoDiSquadra(false);
    }

    /*
     * Inizializza i dati di nemico mappa.
     */
    public void inizializzaDatiNemicoMappa(DatiCombattimentoMappa datiCombattimentoMappa,
                                           Nemico nemico) {
        datiCombattimentoMappa.setNemicoAttuale(nemico);
    }

    /*
     * Inizializza i dati di eroe mappa.
     */
    public void inizializzaDatiEroeMappa(DatiCombattimentoMappa datiCombattimentoMappa,
                                         Eroe eroeAttuale) {
        if (eroeAttuale == null) {
            return;
        }

        datiCombattimentoMappa.setEroeAttuale(eroeAttuale);
        datiCombattimentoMappa.setEroeSelezionato(true);
    }

    /*
     * Imposta il combattimento attivo.
     */
    public void impostaCombattimentoAttivo(DatiCombattimentoMappa datiCombattimentoMappa) {
        datiCombattimentoMappa.setCombattimentoAttivo(true);
    }

    /*
     * Restituisce il nome del nemico.
     */
    public String prendiNomeNemico(GestoreIncontriMappa gestoreIncontriMappa,
                                   Nemico nemico) {
        return gestoreIncontriMappa.prendiNomeNemico(nemico);
    }

    /*
     * Restituisce la vita del nemico.
     */
    public int prendiVitaNemico(GestoreIncontriMappa gestoreIncontriMappa,
                                Nemico nemico) {
        return gestoreIncontriMappa.prendiVitaNemico(nemico);
    }

    /*
     * Restituisce la vita massima del nemico.
     */
    public int prendiVitaMassimaNemico(GestoreIncontriMappa gestoreIncontriMappa,
                                       Nemico nemico) {
        return gestoreIncontriMappa.prendiVitaMassimaNemico(nemico);
    }

    /*
     * Controlla se l'eroe è presente.
     */
    public boolean eroePresente(Eroe eroeAttuale) {
        return eroeAttuale != null;
    }

    /*
     * Restituisce il nome dell'eroe.
     */
    public String prendiNomeEroe(Eroe eroeAttuale) {
        return eroeAttuale.getNome();
    }

    /*
     * Restituisce la vita dell'eroe.
     */
    public int prendiVitaEroe(Eroe eroeAttuale) {
        return eroeAttuale.getVita();
    }

    /*
     * Restituisce la vita massima dell'eroe.
     */
    public int prendiVitaMassimaEroe(Eroe eroeAttuale) {
        return eroeAttuale.getVitaMassima();
    }

    /*
     * Restituisce il danno dell'eroe.
     */
    public int prendiDannoEroe(Eroe eroeAttuale) {
        return eroeAttuale.calcolaAttacco();
    }

    /*
     * Resetta lo stato completo del combattimento sulla mappa.
     */
    public void resettaStatoCombattimentoMappaCompleto(DatiCombattimentoMappa datiCombattimentoMappa) {
        resettaStatoCombattimentoMappa(datiCombattimentoMappa);
    }

    /*
     * Resetta le variabili del combattimento sulla mappa.
     */
    public void resettaVariabiliCombattimentoMappa(DatiCombattimentoMappa datiCombattimentoMappa) {
        resettaStatoCombattimentoMappa(datiCombattimentoMappa);
    }
}