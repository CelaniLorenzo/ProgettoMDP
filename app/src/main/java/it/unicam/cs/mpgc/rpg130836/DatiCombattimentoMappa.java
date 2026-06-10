package it.unicam.cs.mpgc.rpg130836;

public class DatiCombattimentoMappa {

    private Nemico nemicoAttuale;
    private Eroe eroeAttuale;

    private boolean combattimentoAttivo;
    private boolean attaccoSquadraUsato;
    private boolean potenziamentoDisponibile;
    private boolean eroeSelezionato;
    private boolean potenziamentoDiSquadra;
    private Eroe eroeDaPotenziare;

    public Nemico getNemicoAttuale() {
        return nemicoAttuale;
    }

    public void setNemicoAttuale(Nemico nemicoAttuale) {
        this.nemicoAttuale = nemicoAttuale;
    }

    public Eroe getEroeAttuale() {
        return eroeAttuale;
    }

    public void setEroeAttuale(Eroe eroeAttuale) {
        this.eroeAttuale = eroeAttuale;
    }

    public boolean isCombattimentoAttivo() {
        return combattimentoAttivo;
    }

    public void setCombattimentoAttivo(boolean combattimentoAttivo) {
        this.combattimentoAttivo = combattimentoAttivo;
    }

    public boolean isAttaccoSquadraUsato() {
        return attaccoSquadraUsato;
    }

    public void setAttaccoSquadraUsato(boolean attaccoSquadraUsato) {
        this.attaccoSquadraUsato = attaccoSquadraUsato;
    }

    public boolean isPotenziamentoDisponibile() {
        return potenziamentoDisponibile;
    }

    public void setPotenziamentoDisponibile(boolean potenziamentoDisponibile) {
        this.potenziamentoDisponibile = potenziamentoDisponibile;
    }

    public boolean isEroeSelezionato() {
        return eroeSelezionato;
    }

    public void setEroeSelezionato(boolean eroeSelezionato) {
        this.eroeSelezionato = eroeSelezionato;
    }
    public boolean isPotenziamentoDiSquadra() {
        return potenziamentoDiSquadra;
    }

    public void setPotenziamentoDiSquadra(boolean potenziamentoDiSquadra) {
        this.potenziamentoDiSquadra = potenziamentoDiSquadra;
    }

    public Eroe getEroeDaPotenziare() {
        return eroeDaPotenziare;
    }

    public void setEroeDaPotenziare(Eroe eroeDaPotenziare) {
        this.eroeDaPotenziare = eroeDaPotenziare;
    }
}