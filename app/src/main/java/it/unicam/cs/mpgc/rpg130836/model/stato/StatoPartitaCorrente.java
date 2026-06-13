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
    public Eroe getEroeAttuale() {
        return eroeAttuale;
    }

    public void setEroeAttuale(Eroe eroeAttuale) {
        this.eroeAttuale = eroeAttuale;
    }

    public Nemico getNemicoAttuale() {
        return nemicoAttuale;
    }

    public void setNemicoAttuale(Nemico nemicoAttuale) {
        this.nemicoAttuale = nemicoAttuale;
    }

    public boolean isPotenziamentoDisponibile() {
        return potenziamentoDisponibile;
    }

    public void setPotenziamentoDisponibile(boolean potenziamentoDisponibile) {
        this.potenziamentoDisponibile = potenziamentoDisponibile;
    }

    public Eroe getEroeDaPotenziare() {
        return eroeDaPotenziare;
    }

    public void setEroeDaPotenziare(Eroe eroeDaPotenziare) {
        this.eroeDaPotenziare = eroeDaPotenziare;
    }

    public boolean isPotenziamentoDiSquadra() {
        return potenziamentoDiSquadra;
    }

    public void setPotenziamentoDiSquadra(boolean potenziamentoDiSquadra) {
        this.potenziamentoDiSquadra = potenziamentoDiSquadra;
    }
    public void setEroeSelezionato(boolean eroeSelezionato) {
        this.eroeSelezionato = eroeSelezionato;
    }
    public void setAttaccoSquadraUsato(boolean attaccoSquadraUsato) {
        this.attaccoSquadraUsato = attaccoSquadraUsato;
    }
    public void aggiornaEroeSelezionato(Eroe eroe) {
        this.eroeAttuale = eroe;
        this.nomeGiocatoreAttuale = eroe.getNome();
        this.vitaGiocatoreAttuale = eroe.getVita();
        this.vitaGiocatoreMassima = eroe.getVitaMassima();
        this.dannoGiocatore = eroe.calcolaAttacco();
        this.eroeSelezionato = true;
    }
    public void setNumeroIncontro(int numeroIncontro) {
        this.numeroIncontro = numeroIncontro;
    }
    public void setNomeNemicoAttuale(String nomeNemicoAttuale) {
        this.nomeNemicoAttuale = nomeNemicoAttuale;
    }

    public void setVitaNemicoAttuale(int vitaNemicoAttuale) {
        this.vitaNemicoAttuale = vitaNemicoAttuale;
    }

    public void setVitaNemicoMassima(int vitaNemicoMassima) {
        this.vitaNemicoMassima = vitaNemicoMassima;
    }
    public void aggiornaNemicoAttuale(Nemico nemico) {
        this.nemicoAttuale = nemico;
        this.nomeNemicoAttuale = nemico.getNome();
        this.vitaNemicoAttuale = nemico.getVita();
        this.vitaNemicoMassima = nemico.getVitaMassima();
    }




}
