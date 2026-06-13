package it.unicam.cs.mpgc.rpg130836.model.stato;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.DatiPersonaggio;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import java.util.ArrayList;
import java.util.List;

public class StatoGioco {

    private List<DatiPersonaggio> eroi;
    private List<DatiPersonaggio> nemici;
    private int incontriDisputati;
    private String ultimoVincitore;
    private String esitoPartita;
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

    /*
     * Costruisce un'istanza di StatoGioco.
     */
    public StatoGioco() {
        this.eroi = new ArrayList<>();
        this.nemici = new ArrayList<>();
        this.ultimoVincitore = "Nessuno";
        this.esitoPartita = "Partita non conclusa";
    }

    /*
     * Costruisce un'istanza di StatoGioco.
     */
    public StatoGioco(
            List<DatiPersonaggio> eroi,
            List<DatiPersonaggio> nemici,
            int incontriDisputati,
            String ultimoVincitore,
            String esitoPartita
    ) {
        this.eroi = eroi;
        this.nemici = nemici;
        this.incontriDisputati = incontriDisputati;
        this.ultimoVincitore = ultimoVincitore;
        this.esitoPartita = esitoPartita;
    }

    /*
     * Crea lo stato a partire dai dati della partita.
     */
    public static StatoGioco creaDaPartita(
            List<Eroe> eroi,
            List<Nemico> nemici,
            int incontriDisputati,
            String ultimoVincitore,
            String esitoPartita
    ) {
        List<DatiPersonaggio> datiEroi = creaDatiEroi(eroi);
        List<DatiPersonaggio> datiNemici = creaDatiNemici(nemici);

        return new StatoGioco(
                datiEroi,
                datiNemici,
                incontriDisputati,
                ultimoVincitore,
                esitoPartita
        );
    }

    /*
     * Crea lo stato a partire dai dati della schermata grafica.
     */
    public static StatoGioco creaDaGui(
            int numeroIncontro,
            boolean eroeSelezionato,
            boolean attaccoSquadraUsato,
            String nomeGiocatoreAttuale,
            int vitaGiocatoreAttuale,
            int vitaGiocatoreMassima,
            int dannoGiocatore,
            String nomeNemicoAttuale,
            int vitaNemicoAttuale,
            int vitaNemicoMassima
    ) {
        StatoGioco stato = new StatoGioco();

        stato.numeroIncontro = numeroIncontro;
        stato.eroeSelezionato = eroeSelezionato;
        stato.attaccoSquadraUsato = attaccoSquadraUsato;

        stato.nomeGiocatoreAttuale = nomeGiocatoreAttuale;
        stato.vitaGiocatoreAttuale = vitaGiocatoreAttuale;
        stato.vitaGiocatoreMassima = vitaGiocatoreMassima;
        stato.dannoGiocatore = dannoGiocatore;

        stato.nomeNemicoAttuale = nomeNemicoAttuale;
        stato.vitaNemicoAttuale = vitaNemicoAttuale;
        stato.vitaNemicoMassima = vitaNemicoMassima;

        return stato;
    }

    /*
     * Crea i dati di eroi.
     */
    private static List<DatiPersonaggio> creaDatiEroi(List<Eroe> eroi) {
        List<DatiPersonaggio> dati = new ArrayList<>();

        for (Eroe eroe : eroi) {
            dati.add(DatiPersonaggio.daPersonaggio(eroe));
        }

        return dati;
    }

    /*
     * Crea i dati di nemici.
     */
    private static List<DatiPersonaggio> creaDatiNemici(List<Nemico> nemici) {
        List<DatiPersonaggio> dati = new ArrayList<>();

        for (Nemico nemico : nemici) {
            dati.add(DatiPersonaggio.daPersonaggio(nemico));
        }

        return dati;
    }

    /*
     * Restituisce gli eroi.
     */
    public List<DatiPersonaggio> getEroi() {
        return eroi;
    }

    /*
     * Restituisce i nemici.
     */
    public List<DatiPersonaggio> getNemici() {
        return nemici;
    }

    /*
     * Restituisce gli incontri disputati.
     */
    public int getIncontriDisputati() {
        return incontriDisputati;
    }

    /*
     * Restituisce l'ultimo vincitore.
     */
    public String getUltimoVincitore() {
        return ultimoVincitore;
    }

    /*
     * Restituisce l'esito partita.
     */
    public String getEsitoPartita() {
        return esitoPartita;
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

}