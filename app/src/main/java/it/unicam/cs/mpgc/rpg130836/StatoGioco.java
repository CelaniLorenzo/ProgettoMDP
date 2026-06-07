package it.unicam.cs.mpgc.rpg130836;

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

    public StatoGioco() {
        this.eroi = new ArrayList<>();
        this.nemici = new ArrayList<>();
        this.ultimoVincitore = "Nessuno";
        this.esitoPartita = "Partita non conclusa";
    }

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

    private static List<DatiPersonaggio> creaDatiEroi(List<Eroe> eroi) {
        List<DatiPersonaggio> dati = new ArrayList<>();

        for (Eroe eroe : eroi) {
            dati.add(DatiPersonaggio.daPersonaggio(eroe));
        }

        return dati;
    }

    private static List<DatiPersonaggio> creaDatiNemici(List<Nemico> nemici) {
        List<DatiPersonaggio> dati = new ArrayList<>();

        for (Nemico nemico : nemici) {
            dati.add(DatiPersonaggio.daPersonaggio(nemico));
        }

        return dati;
    }

    public List<DatiPersonaggio> getEroi() {
        return eroi;
    }

    public List<DatiPersonaggio> getNemici() {
        return nemici;
    }

    public int getIncontriDisputati() {
        return incontriDisputati;
    }

    public String getUltimoVincitore() {
        return ultimoVincitore;
    }

    public String getEsitoPartita() {
        return esitoPartita;
    }
    public int getNumeroIncontro() {
        return numeroIncontro;
    }

    public boolean isEroeSelezionato() {
        return eroeSelezionato;
    }

    public boolean isAttaccoSquadraUsato() {
        return attaccoSquadraUsato;
    }

    public String getNomeGiocatoreAttuale() {
        return nomeGiocatoreAttuale;
    }

    public int getVitaGiocatoreAttuale() {
        return vitaGiocatoreAttuale;
    }

    public int getVitaGiocatoreMassima() {
        return vitaGiocatoreMassima;
    }

    public int getDannoGiocatore() {
        return dannoGiocatore;
    }

    public String getNomeNemicoAttuale() {
        return nomeNemicoAttuale;
    }

    public int getVitaNemicoAttuale() {
        return vitaNemicoAttuale;
    }

    public int getVitaNemicoMassima() {
        return vitaNemicoMassima;
    }

}