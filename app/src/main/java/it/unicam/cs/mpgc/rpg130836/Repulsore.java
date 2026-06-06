package it.unicam.cs.mpgc.rpg130836;

public class Repulsore implements AbilitaSpeciale {

    private int livello;

    public Repulsore() {
        this.livello = 1;
    }

    @Override
    public String getNome() {
        return "Repulsore";
    }

    @Override
    public int getBonusAttacco() {
        return 5 + livello * 2;
    }

    @Override
    public int getBonusDifesa() {
        return livello;
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