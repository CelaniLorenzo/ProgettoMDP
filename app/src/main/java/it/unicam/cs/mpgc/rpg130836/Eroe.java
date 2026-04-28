package it.unicam.cs.mpgc.rpg130836;

public abstract class Eroe extends Personaggio {

    private final Armatura armatura;

    protected Eroe(String nome, int puntiVita, int attacco, int difesa, Armatura armatura) {
        super(nome, puntiVita, attacco, difesa);

        if (armatura == null) {
            throw new IllegalArgumentException("L'armatura non può essere nulla");
        }

        this.armatura = armatura;
    }

    public Armatura getArmatura() {
        return armatura;
    }

    @Override
    public int getAttacco() {
        return super.getAttacco() + armatura.getBonusAttacco();
    }

    @Override
    public int getDifesa() {
        return super.getDifesa() + armatura.getBonusDifesa();
    }

    @Override
    public void attacca(Personaggio bersaglio) {
        System.out.println(getNome() + " usa l'abilità: " + armatura.getAbilitaSpeciale().getNome());
        bersaglio.difenditi(getAttacco());
    }
}
