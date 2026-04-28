package it.unicam.cs.mpgc.rpg130836;

public class ForzaHulk implements AbilitaSpeciale {

    private int livello;
    private int forza;

    public ForzaHulk() {
        this.livello = 1;
        this.forza = 20;
    }
    @Override
    public String getNome() {
        return "Forza Hulk";
    }
    @Override
    public int getLivello() {
        return livello;
    }
    @Override
    public int getBonusAttacco() {
        return forza;
    }
    @Override
    public int getBonusDifesa() {
        return 0;
    }
    @Override
    public String getDescrizione() {
        return "Iron Hulk aumenta la forza distruttiva quando rompe.";
    }
    @Override
    public void potenzia() {
        livello++;
        forza += 12;
    }
}
