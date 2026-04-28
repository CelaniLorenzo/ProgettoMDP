package it.unicam.cs.mpgc.rpg130836;

public class Repulsore implements AbilitaSpeciale {

    private int livello;
    private int potenza;

    public Repulsore() {
        this.livello = 1;
        this.potenza = 15;
    }
    @Override
    public String getNome() {
        return "Repulsore";
    }
    @Override
    public int getLivello() {
        return livello;
    }
    @Override
    public int getBonusAttacco() {
        return potenza;
    }
    @Override
    public int getBonusDifesa() {
        return 0;
    }
    @Override
    public String getDescrizione() {
        return "Il repulsore di Iron Man diventa più potente dopo ogni vittoria.";
    }
    @Override
    public void potenzia() {
        livello++;
        potenza += 10;
    }
}
