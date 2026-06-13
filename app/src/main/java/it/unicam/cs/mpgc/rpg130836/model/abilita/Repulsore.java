package it.unicam.cs.mpgc.rpg130836.model.abilita;

public class Repulsore implements AbilitaSpeciale {

    private int livello;

    /*
     * Costruisce un'istanza di Repulsore.
     */
    public Repulsore() {
        this.livello = 1;
    }

    /*
     * Restituisce il nome.
     */
    @Override
    public String getNome() {
        return "Repulsore";
    }

    /*
     * Restituisce il bonus d'attacco.
     */
    @Override
    public int getBonusAttacco() {
        return 5 + livello * 2;
    }

    /*
     * Restituisce il bonus di difesa.
     */
    @Override
    public int getBonusDifesa() {
        return livello;
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