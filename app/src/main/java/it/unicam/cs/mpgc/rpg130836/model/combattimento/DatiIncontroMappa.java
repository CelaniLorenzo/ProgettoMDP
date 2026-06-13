package it.unicam.cs.mpgc.rpg130836.model.combattimento;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;

public class DatiIncontroMappa {

    private final Nemico nemicoAttuale;
    private final String nomeNemicoAttuale;
    private final int vitaNemicoAttuale;
    private final int vitaNemicoMassima;

    private final String nomeGiocatoreAttuale;
    private final int vitaGiocatoreAttuale;
    private final int vitaGiocatoreMassima;
    private final int dannoGiocatore;
    private final boolean eroeSelezionato;

    public DatiIncontroMappa(
            Nemico nemicoAttuale,
            String nomeNemicoAttuale,
            int vitaNemicoAttuale,
            int vitaNemicoMassima,
            String nomeGiocatoreAttuale,
            int vitaGiocatoreAttuale,
            int vitaGiocatoreMassima,
            int dannoGiocatore,
            boolean eroeSelezionato
    ) {
        this.nemicoAttuale = nemicoAttuale;
        this.nomeNemicoAttuale = nomeNemicoAttuale;
        this.vitaNemicoAttuale = vitaNemicoAttuale;
        this.vitaNemicoMassima = vitaNemicoMassima;
        this.nomeGiocatoreAttuale = nomeGiocatoreAttuale;
        this.vitaGiocatoreAttuale = vitaGiocatoreAttuale;
        this.vitaGiocatoreMassima = vitaGiocatoreMassima;
        this.dannoGiocatore = dannoGiocatore;
        this.eroeSelezionato = eroeSelezionato;
    }

    public Nemico getNemicoAttuale() { return nemicoAttuale; }
    public String getNomeNemicoAttuale() { return nomeNemicoAttuale; }
    public int getVitaNemicoAttuale() { return vitaNemicoAttuale; }
    public int getVitaNemicoMassima() { return vitaNemicoMassima; }
    public String getNomeGiocatoreAttuale() { return nomeGiocatoreAttuale; }
    public int getVitaGiocatoreAttuale() { return vitaGiocatoreAttuale; }
    public int getVitaGiocatoreMassima() { return vitaGiocatoreMassima; }
    public int getDannoGiocatore() { return dannoGiocatore; }
    public boolean isEroeSelezionato() { return eroeSelezionato; }
}