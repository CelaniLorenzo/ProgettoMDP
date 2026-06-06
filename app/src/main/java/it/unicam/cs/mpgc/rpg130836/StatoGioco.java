package it.unicam.cs.mpgc.rpg130836;

import java.util.ArrayList;
import java.util.List;

public class StatoGioco {

    private List<DatiPersonaggio> eroi;
    private List<DatiPersonaggio> nemici;
    private int incontriDisputati;
    private String ultimoVincitore;
    private String esitoPartita;

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
}