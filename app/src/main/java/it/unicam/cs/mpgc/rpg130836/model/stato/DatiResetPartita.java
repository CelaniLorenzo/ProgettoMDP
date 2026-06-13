package it.unicam.cs.mpgc.rpg130836.model.stato;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import java.util.List;

public class DatiResetPartita {

    private final List<Eroe> eroi;
    private final List<Nemico> nemici;
    private final int numeroIncontro;
    private final String nomeNemicoAttuale;
    private final int vitaNemicoAttuale;
    private final int vitaNemicoMassima;
    private final int vitaGiocatoreAttuale;
    private final int vitaGiocatoreMassima;
    private final String nomeGiocatoreAttuale;
    private final int dannoGiocatore;
    private final boolean eroeSelezionato;
    private final boolean giocatoreAttaccaPerPrimo;
    private final boolean attaccoSquadraUsato;
    private final boolean potenziamentoDisponibile;
    private final Eroe eroeDaPotenziare;
    private final Eroe eroeAttuale;
    private final Nemico nemicoAttuale;

    /*
     * Costruisce un'istanza di DatiResetPartita.
     */
    public DatiResetPartita(List<Eroe> eroi, List<Nemico> nemici) {
        this.eroi = eroi;
        this.nemici = nemici;

        this.numeroIncontro = 0;
        this.nomeNemicoAttuale = "";
        this.vitaNemicoAttuale = 0;
        this.vitaNemicoMassima = 0;
        this.vitaGiocatoreAttuale = 100;
        this.vitaGiocatoreMassima = 100;
        this.nomeGiocatoreAttuale = "Nessun eroe";
        this.dannoGiocatore = 0;
        this.eroeSelezionato = false;
        this.giocatoreAttaccaPerPrimo = true;
        this.attaccoSquadraUsato = false;
        this.potenziamentoDisponibile = false;
        this.eroeDaPotenziare = null;
        this.eroeAttuale = null;
        this.nemicoAttuale = null;
    }

    /*
     * Restituisce il numero dell'incontro.
     */
    public int getNumeroIncontro() {return numeroIncontro; }

    /*
     * Restituisce il nome del nemico attuale.
     */
    public String getNomeNemicoAttuale() { return nomeNemicoAttuale; }

    /*
     * Restituisce la vita del nemico attuale.
     */
    public int getVitaNemicoAttuale() { return vitaNemicoAttuale; }

    /*
     * Restituisce la vita massima del nemico.
     */
    public int getVitaNemicoMassima() { return vitaNemicoMassima; }

    /*
     * Restituisce la vita del giocatore attuale.
     */
    public int getVitaGiocatoreAttuale() { return vitaGiocatoreAttuale; }

    /*
     * Restituisce la vita massima del giocatore.
     */
    public int getVitaGiocatoreMassima() { return vitaGiocatoreMassima; }

    /*
     * Restituisce il nome del giocatore attuale.
     */
    public String getNomeGiocatoreAttuale() { return nomeGiocatoreAttuale; }

    /*
     * Restituisce il danno del giocatore.
     */
    public int getDannoGiocatore() { return dannoGiocatore; }

    /*
     * Controlla se l'eroe è selezionato.
     */
    public boolean isEroeSelezionato() { return eroeSelezionato; }

    /*
     * Controlla se il giocatore attacca per primo è valido.
     */
    public boolean isGiocatoreAttaccaPerPrimo() { return giocatoreAttaccaPerPrimo; }

    /*
     * Controlla se l'attacco di squadra è stato usato.
     */
    public boolean isAttaccoSquadraUsato() { return attaccoSquadraUsato; }

    /*
     * Controlla se il potenziamento è disponibile.
     */
    public boolean isPotenziamentoDisponibile() { return potenziamentoDisponibile; }

    /*
     * Restituisce gli eroi.
     */
    public List<Eroe> getEroi() {
        return eroi;
    }

    /*
     * Restituisce i nemici.
     */
    public List<Nemico> getNemici() {
        return nemici;
    }

    /*
     * Restituisce l'eroe da potenziare.
     */
    public Eroe getEroeDaPotenziare() {
        return eroeDaPotenziare;
    }

    /*
     * Restituisce l'eroe attuale.
     */
    public Eroe getEroeAttuale() {
        return eroeAttuale;
    }

    /*
     * Restituisce il nemico attuale.
     */
    public Nemico getNemicoAttuale() {
        return nemicoAttuale;
    }
}
