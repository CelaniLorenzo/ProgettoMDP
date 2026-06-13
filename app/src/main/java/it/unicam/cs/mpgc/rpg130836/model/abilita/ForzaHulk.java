package it.unicam.cs.mpgc.rpg130836.model.abilita;

public class ForzaHulk implements AbilitaSpeciale {

    private int livello;

    /*
     * Costruisce un'istanza di ForzaHulk.
     */
    public ForzaHulk() {
        this.livello = 1;
    }

    /*
     * Restituisce il nome.
     */
    @Override
    public String getNome() {
        return "Forza Hulk";
    }

    /*
     * Restituisce il bonus d'attacco.
     */
    @Override
    public int getBonusAttacco() {
        return 7 + livello * 3;
    }

    /*
     * Restituisce il bonus di difesa.
     */
    @Override
    public int getBonusDifesa() {
        return 3 + livello;
    }

    /*
     * Applica il potenziamento disponibile.
     */
    @Override
    public void potenzia() {
        livello++;
    }

    /*
     * Restituisce la descrizione del potenziamento.
     */
    @Override
    public String descrizionePotenziamento() {
        return getNome() + " livello " + livello;
    }
}