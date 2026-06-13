package it.unicam.cs.mpgc.rpg130836.model.stato;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;

public class StatoPartitaCorrente {

    private int numeroIncontro;
    private boolean eroeSelezionato;
    private boolean attaccoSquadraUsato;

    private String nomeGiocatoreAttuale;
    private int vitaGiocatoreAttuale;
    private int vitaGiocatoreMassima;
    private int dannoGiocatore;

    private String nomeNemicoAttuale;
    private int vitaNemicoAttuale;
    private int vitaNemicoMassima;
    private Eroe eroeAttuale;
    private Nemico nemicoAttuale;
    private boolean potenziamentoDisponibile;
    private Eroe eroeDaPotenziare;
    private boolean potenziamentoDiSquadra;

    /*
     * Aggiorna lo stato corrente usando lo stato di gioco.
     */
    public void aggiornaDaStatoGioco(StatoGioco statoGioco) {
        numeroIncontro = statoGioco.getNumeroIncontro();
        eroeSelezionato = statoGioco.isEroeSelezionato();
        attaccoSquadraUsato = statoGioco.isAttaccoSquadraUsato();

        nomeGiocatoreAttuale = statoGioco.getNomeGiocatoreAttuale();
        vitaGiocatoreAttuale = statoGioco.getVitaGiocatoreAttuale();
        vitaGiocatoreMassima = statoGioco.getVitaGiocatoreMassima();
        dannoGiocatore = statoGioco.getDannoGiocatore();

        nomeNemicoAttuale = statoGioco.getNomeNemicoAttuale();
        vitaNemicoAttuale = statoGioco.getVitaNemicoAttuale();
        vitaNemicoMassima = statoGioco.getVitaNemicoMassima();
    }

    /*
     * Restituisce il numero dell'incontro.
     */
    public int getNumeroIncontro() {
        return numeroIncontro;
    }

    /*
     * Controlla se l'eroe è selezionato.
     */
    public boolean isEroeSelezionato() {
        return eroeSelezionato;
    }

    /*
     * Controlla se l'attacco di squadra è stato usato.
     */
    public boolean isAttaccoSquadraUsato() {
        return attaccoSquadraUsato;
    }

    /*
     * Restituisce il nome del giocatore attuale.
     */
    public String getNomeGiocatoreAttuale() {
        return nomeGiocatoreAttuale;
    }

    /*
     * Restituisce la vita del giocatore attuale.
     */
    public int getVitaGiocatoreAttuale() {
        return vitaGiocatoreAttuale;
    }

    /*
     * Restituisce la vita massima del giocatore.
     */
    public int getVitaGiocatoreMassima() {
        return vitaGiocatoreMassima;
    }

    /*
     * Restituisce il danno del giocatore.
     */
    public int getDannoGiocatore() {
        return dannoGiocatore;
    }

    /*
     * Restituisce il nome del nemico attuale.
     */
    public String getNomeNemicoAttuale() {
        return nomeNemicoAttuale;
    }

    /*
     * Restituisce la vita del nemico attuale.
     */
    public int getVitaNemicoAttuale() {
        return vitaNemicoAttuale;
    }

    /*
     * Restituisce la vita massima del nemico.
     */
    public int getVitaNemicoMassima() {
        return vitaNemicoMassima;
    }

    /*
     * Aggiorna lo stato corrente usando i dati di reset.
     */
    public void aggiornaDaReset(DatiResetPartita datiResetPartita) {
        numeroIncontro = datiResetPartita.getNumeroIncontro();

        nomeNemicoAttuale = datiResetPartita.getNomeNemicoAttuale();
        vitaNemicoAttuale = datiResetPartita.getVitaNemicoAttuale();
        vitaNemicoMassima = datiResetPartita.getVitaNemicoMassima();

        vitaGiocatoreAttuale = datiResetPartita.getVitaGiocatoreAttuale();
        vitaGiocatoreMassima = datiResetPartita.getVitaGiocatoreMassima();
        nomeGiocatoreAttuale = datiResetPartita.getNomeGiocatoreAttuale();

        dannoGiocatore = datiResetPartita.getDannoGiocatore();
        eroeSelezionato = datiResetPartita.isEroeSelezionato();

        attaccoSquadraUsato =
                datiResetPartita.isAttaccoSquadraUsato();
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
     * Imposta l'eroe selezionato.
     */
    public void setEroeSelezionato(boolean eroeSelezionato) {
        this.eroeSelezionato = eroeSelezionato;
    }

    /*
     * Imposta l'attacco di squadra usato.
     */
    public void setAttaccoSquadraUsato(boolean attaccoSquadraUsato) {
        this.attaccoSquadraUsato = attaccoSquadraUsato;
    }

    /*
     * Aggiorna l'eroe selezionato.
     */
    public void aggiornaEroeSelezionato(Eroe eroe) {
        this.eroeAttuale = eroe;
        this.nomeGiocatoreAttuale = eroe.getNome();
        this.vitaGiocatoreAttuale = eroe.getVita();
        this.vitaGiocatoreMassima = eroe.getVitaMassima();
        this.dannoGiocatore = eroe.calcolaAttacco();
        this.eroeSelezionato = true;
    }

    /*
     * Imposta il numero dell'incontro.
     */
    public void setNumeroIncontro(int numeroIncontro) {
        this.numeroIncontro = numeroIncontro;
    }

    /*
     * Imposta il nome del nemico attuale.
     */
    public void setNomeNemicoAttuale(String nomeNemicoAttuale) {
        this.nomeNemicoAttuale = nomeNemicoAttuale;
    }

    /*
     * Imposta la vita del nemico attuale.
     */
    public void setVitaNemicoAttuale(int vitaNemicoAttuale) {
        this.vitaNemicoAttuale = vitaNemicoAttuale;
    }

    /*
     * Imposta la vita massima del nemico.
     */
    public void setVitaNemicoMassima(int vitaNemicoMassima) {
        this.vitaNemicoMassima = vitaNemicoMassima;
    }

    /*
     * Aggiorna il nemico attuale.
     */
    public void aggiornaNemicoAttuale(Nemico nemico) {
        this.nemicoAttuale = nemico;
        this.nomeNemicoAttuale = nemico.getNome();
        this.vitaNemicoAttuale = nemico.getVita();
        this.vitaNemicoMassima = nemico.getVitaMassima();
    }




}
