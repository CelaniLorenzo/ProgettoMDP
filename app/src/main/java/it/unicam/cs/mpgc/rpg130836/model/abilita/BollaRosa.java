package it.unicam.cs.mpgc.rpg130836.model.abilita;

public class BollaRosa implements AbilitaSpeciale {

    private int livello;

    public BollaRosa() {
        this.livello = 1;
    }

    @Override
    public String getNome() {
        return "Bolla Rosa";
    }

    @Override
    public int getBonusAttacco() {
        return 2 + livello;
    }

    @Override
    public int getBonusDifesa() {
        return 6 + livello * 2;
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