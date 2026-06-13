package it.unicam.cs.mpgc.rpg130836.model.personaggi;

import it.unicam.cs.mpgc.rpg130836.model.abilita.Armatura;
import it.unicam.cs.mpgc.rpg130836.model.abilita.Potenziabile;
import java.util.Objects;

public class Eroe extends Personaggio implements Potenziabile {

    private final Armatura armatura;

    /*
     * Costruisce un'istanza di Eroe.
     */
    public Eroe(String nome, int vitaMassima, int attaccoBase, int difesaBase, Armatura armatura) {
        super(nome, vitaMassima, attaccoBase, difesaBase);
        this.armatura = Objects.requireNonNull(armatura, "L'armatura non può essere null.");
    }

    /*
     * Calcola il valore totale dell'attacco.
     */
    @Override
    public int calcolaAttacco() {
        return super.calcolaAttacco() + armatura.calcolaBonusAttacco();
    }

    /*
     * Calcola il valore totale della difesa.
     */
    @Override
    public int calcolaDifesa() {
        return super.calcolaDifesa() + armatura.calcolaBonusDifesa();
    }

    /*
     * Applica il potenziamento disponibile.
     */
    @Override
    public void potenzia() {
        armatura.potenzia();
    }

    /*
     * Restituisce la descrizione del potenziamento.
     */
    @Override
    public String descrizionePotenziamento() {
        return armatura.descrizionePotenziamento();
    }

    /*
     * Restituisce una rappresentazione testuale dell'oggetto.
     */
    @Override
    public String toString() {
        return super.toString() + " | Armatura: " + armatura.getNome();
    }

    /*
     * Restituisce l'armatura.
     */
    public Armatura getArmatura() {
        return armatura;
    }
}