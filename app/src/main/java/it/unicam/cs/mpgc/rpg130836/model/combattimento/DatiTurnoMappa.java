package it.unicam.cs.mpgc.rpg130836.model.combattimento;

public class DatiTurnoMappa {

    private final int vitaGiocatoreAttuale;
    private final int vitaGiocatoreMassima;
    private final int vitaNemicoAttuale;
    private final int vitaNemicoMassima;
    private final boolean nemicoSconfitto;

    /*
     * Costruisce un'istanza di DatiTurnoMappa.
     */
    public DatiTurnoMappa(
            int vitaGiocatoreAttuale,
            int vitaGiocatoreMassima,
            int vitaNemicoAttuale,
            int vitaNemicoMassima,
            boolean nemicoSconfitto
    ) {
        this.vitaGiocatoreAttuale = vitaGiocatoreAttuale;
        this.vitaGiocatoreMassima = vitaGiocatoreMassima;
        this.vitaNemicoAttuale = vitaNemicoAttuale;
        this.vitaNemicoMassima = vitaNemicoMassima;
        this.nemicoSconfitto = nemicoSconfitto;
    }

    public int getVitaGiocatoreAttuale() { return vitaGiocatoreAttuale; }
    public int getVitaGiocatoreMassima() { return vitaGiocatoreMassima; }
    public int getVitaNemicoAttuale() { return vitaNemicoAttuale; }
    public int getVitaNemicoMassima() { return vitaNemicoMassima; }
    public boolean isNemicoSconfitto() { return nemicoSconfitto; }
}