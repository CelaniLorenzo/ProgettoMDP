package it.unicam.cs.mpgc.rpg130836;

public abstract class Eroe extends Personaggio implements Potenziabile {

    private final Armatura armatura;

    protected Eroe(String nome, int vitaMassima, int attaccoBase, int difesaBase, Armatura armatura) {
        super(nome, vitaMassima, attaccoBase, difesaBase);

        if (armatura == null) {
            throw new IllegalArgumentException("L'armatura non può essere null.");
        }

        this.armatura = armatura;
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

    public Armatura getArmatura() {
        return armatura;
    }
}