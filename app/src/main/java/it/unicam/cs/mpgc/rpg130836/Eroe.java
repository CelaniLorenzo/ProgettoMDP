package it.unicam.cs.mpgc.rpg130836;

import java.util.Objects;

public class Eroe extends Personaggio implements Potenziabile {

    private final Armatura armatura;

    public Eroe(String nome, int vitaMassima, int attaccoBase, int difesaBase, Armatura armatura) {
        super(nome, vitaMassima, attaccoBase, difesaBase);
        this.armatura = Objects.requireNonNull(armatura, "L'armatura non può essere null.");
    }

    @Override
    public int calcolaAttacco() {
        return super.calcolaAttacco() + armatura.calcolaBonusAttacco();
    }

    @Override
    public int calcolaDifesa() {
        return super.calcolaDifesa() + armatura.calcolaBonusDifesa();
    }

    @Override
    public void potenzia() {
        armatura.potenzia();
    }

    @Override
    public String descrizionePotenziamento() {
        return armatura.descrizionePotenziamento();
    }

    @Override
    public String toString() {
        return super.toString() + " | Armatura: " + armatura.getNome();
    }

    public Armatura getArmatura() {
        return armatura;
    }
}