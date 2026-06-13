package it.unicam.cs.mpgc.rpg130836.model.abilita;

public interface AbilitaSpeciale extends Potenziabile {

    /*
     * Restituisce il nome.
     */
    String getNome();

    /*
     * Restituisce il bonus d'attacco.
     */
    int getBonusAttacco();

    /*
     * Restituisce il bonus di difesa.
     */
    int getBonusDifesa();
}