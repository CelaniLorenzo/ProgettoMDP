package it.unicam.cs.mpgc.rpg130836.model.combattimento;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;

public class DatiCombattimentoMappa {

    private Nemico nemicoAttuale;
    private Eroe eroeAttuale;

    private boolean combattimentoAttivo;
    private boolean attaccoSquadraUsato;
    private boolean potenziamentoDisponibile;
    private boolean eroeSelezionato;
    private boolean potenziamentoDiSquadra;
    private Eroe eroeDaPotenziare;

    /*
     * Restituisce il nemico attuale.
     */
    public Nemico getNemicoAttuale() {
        return nemicoAttuale;
    }

    /*
     * Imposta il nemico attuale.
     */
    public void setNemicoAttuale(Nemico nemicoAttuale) {
        this.nemicoAttuale = nemicoAttuale;
    }

    /*
     * Restituisce l'eroe attuale.
     */
    public Eroe getEroeAttuale() {
        return eroeAttuale;
    }

    /*
     * Imposta l'eroe attuale.
     */
    public void setEroeAttuale(Eroe eroeAttuale) {
        this.eroeAttuale = eroeAttuale;
    }

    /*
     * Controlla se il combattimento attivo è valido.
     */
    public boolean isCombattimentoAttivo() {
        return combattimentoAttivo;
    }

    /*
     * Imposta il combattimento attivo.
     */
    public void setCombattimentoAttivo(boolean combattimentoAttivo) {
        this.combattimentoAttivo = combattimentoAttivo;
    }

    /*
     * Controlla se l'attacco di squadra è stato usato.
     */
    public boolean isAttaccoSquadraUsato() {
        return attaccoSquadraUsato;
    }

    /*
     * Imposta l'attacco di squadra usato.
     */
    public void setAttaccoSquadraUsato(boolean attaccoSquadraUsato) {
        this.attaccoSquadraUsato = attaccoSquadraUsato;
    }

    /*
     * Controlla se il potenziamento è disponibile.
     */
    public boolean isPotenziamentoDisponibile() {
        return potenziamentoDisponibile;
    }

    /*
     * Imposta il potenziamento disponibile.
     */
    public void setPotenziamentoDisponibile(boolean potenziamentoDisponibile) {
        this.potenziamentoDisponibile = potenziamentoDisponibile;
    }

    /*
     * Controlla se l'eroe è selezionato.
     */
    public boolean isEroeSelezionato() {
        return eroeSelezionato;
    }

    /*
     * Imposta l'eroe selezionato.
     */
    public void setEroeSelezionato(boolean eroeSelezionato) {
        this.eroeSelezionato = eroeSelezionato;
    }

    /*
     * Controlla se il potenziamento è di squadra.
     */
    public boolean isPotenziamentoDiSquadra() {
        return potenziamentoDiSquadra;
    }

    /*
     * Imposta il potenziamento di squadra.
     */
    public void setPotenziamentoDiSquadra(boolean potenziamentoDiSquadra) {
        this.potenziamentoDiSquadra = potenziamentoDiSquadra;
    }

    /*
     * Restituisce l'eroe da potenziare.
     */
    public Eroe getEroeDaPotenziare() {
        return eroeDaPotenziare;
    }

    /*
     * Imposta l'eroe da potenziare.
     */
    public void setEroeDaPotenziare(Eroe eroeDaPotenziare) {
        this.eroeDaPotenziare = eroeDaPotenziare;
    }
}