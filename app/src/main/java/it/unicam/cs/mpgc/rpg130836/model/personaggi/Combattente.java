package it.unicam.cs.mpgc.rpg130836.model.personaggi;

public interface Combattente {

    /*
     * Restituisce il nome.
     */
    String getNome();

    /*
     * Restituisce la vita.
     */
    int getVita();

    /*
     * Restituisce la vita massima.
     */
    int getVitaMassima();

    /*
     * Controlla se il personaggio è ancora vivo.
     */
    boolean isVivo();

    /*
     * Esegue un attacco contro il bersaglio indicato.
     */
    int attacca(Combattente bersaglio);

    /*
     * Applica il danno ricevuto dal personaggio.
     */
    void riceviDanno(int danno);

    /*
     * Calcola il valore totale dell'attacco.
     */
    int calcolaAttacco();

    /*
     * Calcola il valore totale della difesa.
     */
    int calcolaDifesa();
}
