package it.unicam.cs.mpgc.rpg130836;

public class ForzaHulk implements AbilitaSpeciale {

    private int livello;

    public ForzaHulk() {
        this.livello = 1;
    }

    @Override
    public String getNome() {
        return "Forza Hulk";
    }

    @Override
    public int getBonusAttacco() {
        return 7 + livello * 3;
    }

    @Override
    public int getBonusDifesa() {
        return 3 + livello;
    }

    @Override
    public void potenzia() {
        livello++;
    }

    @Override
    public String descrizionePotenziamento() {
        return getNome() + " livello " + livello;
    }
}