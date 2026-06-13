package it.unicam.cs.mpgc.rpg130836.model.abilita;

public class BollaRosa implements AbilitaSpeciale {

    private int livello;

    /*
     * Costruisce un'istanza di BollaRosa.
     */
    public BollaRosa() {
        this.livello = 1;
    }

    /*
     * Restituisce il nome.
     */
    @Override
    public String getNome() {
        return "Bolla Rosa";
    }

    /*
     * Restituisce il bonus d'attacco.
     */
    @Override
    public int getBonusAttacco() {
        return 2 + livello;
    }

    /*
     * Restituisce il bonus di difesa.
     */
    @Override
    public int getBonusDifesa() {
        return 6 + livello * 2;
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