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
    public int getNumeroIncontro() {return numeroIncontro; }
    public String getNomeNemicoAttuale() { return nomeNemicoAttuale; }
    public int getVitaNemicoAttuale() { return vitaNemicoAttuale; }
    public int getVitaNemicoMassima() { return vitaNemicoMassima; }
    public int getVitaGiocatoreAttuale() { return vitaGiocatoreAttuale; }
    public int getVitaGiocatoreMassima() { return vitaGiocatoreMassima; }
    public String getNomeGiocatoreAttuale() { return nomeGiocatoreAttuale; }
    public int getDannoGiocatore() { return dannoGiocatore; }
    public boolean isEroeSelezionato() { return eroeSelezionato; }
    public boolean isGiocatoreAttaccaPerPrimo() { return giocatoreAttaccaPerPrimo; }
    public boolean isAttaccoSquadraUsato() { return attaccoSquadraUsato; }
    public boolean isPotenziamentoDisponibile() { return potenziamentoDisponibile; }
    public List<Eroe> getEroi() {
        return eroi;
    }

    public List<Nemico> getNemici() {
        return nemici;
    }
    public Eroe getEroeDaPotenziare() {
        return eroeDaPotenziare;
    }

    public Eroe getEroeAttuale() {
        return eroeAttuale;
    }

    public Nemico getNemicoAttuale() {
        return nemicoAttuale;
    }
}
