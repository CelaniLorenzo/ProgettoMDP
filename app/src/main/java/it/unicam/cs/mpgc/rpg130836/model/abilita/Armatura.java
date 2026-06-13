package it.unicam.cs.mpgc.rpg130836.model.abilita;

public class Armatura implements Potenziabile {

    private final String nome;
    private final AbilitaSpeciale abilitaSpeciale;
    private int bonusAttacco;
    private int bonusDifesa;

    /*
     * Costruisce un'istanza di Armatura.
     */
    public Armatura(String nome, AbilitaSpeciale abilitaSpeciale, int bonusAttacco, int bonusDifesa) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Il nome dell'armatura non può essere vuoto.");
        }

        if (abilitaSpeciale == null) {
            throw new IllegalArgumentException("L'abilità speciale non può essere null.");
        }

        if (bonusAttacco < 0 || bonusDifesa < 0) {
            throw new IllegalArgumentException("I bonus dell'armatura non possono essere negativi.");
        }

        this.nome = nome;
        this.abilitaSpeciale = abilitaSpeciale;
        this.bonusAttacco = bonusAttacco;
        this.bonusDifesa = bonusDifesa;
    }

    /*
     * Calcola il bonus d'attacco.
     */
    public int calcolaBonusAttacco() {
        return bonusAttacco + abilitaSpeciale.getBonusAttacco();
    }

    /*
     * Calcola il bonus di difesa.
     */
    public int calcolaBonusDifesa() {
        return bonusDifesa + abilitaSpeciale.getBonusDifesa();
    }

    /*
     * Applica il potenziamento disponibile.
     */
    @Override
    public void potenzia() {
        bonusAttacco += 2;
        bonusDifesa += 2;
        abilitaSpeciale.potenzia();
    }

    /*
     * Restituisce la descrizione del potenziamento.
     */
    @Override
    public String descrizionePotenziamento() {
        return nome + " (+" + bonusAttacco + " att, +" + bonusDifesa + " dif) con "
                + abilitaSpeciale.descrizionePotenziamento();
    }

    /*
     * Restituisce il nome.
     */
    public String getNome() {
        return nome;
    }

    /*
     * Restituisce l'abilita speciale.
     */
    protected AbilitaSpeciale getAbilitaSpeciale() {
        return abilitaSpeciale;
    }

    /*
     * Restituisce una rappresentazione testuale dell'oggetto.
     */
    @Override
    public String toString() {
        return nome + " [ATT +" + calcolaBonusAttacco() +

                /*
                 * Calcola il bonus di difesa.
                 */
                ", DEF +" + calcolaBonusDifesa() + "]";
    }
}