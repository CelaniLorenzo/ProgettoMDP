package it.unicam.cs.mpgc.rpg130836;

public class BollaRosa implements AbilitaSpeciale {

    private int livello;
    private int protezione;

    public BollaRosa() {
        this.livello = 1;
        this.protezione = 15;
    }
    @Override
    public String getNome() {
        return "Bolla Rosa";
    }
    @Override
    public int getLivello() {
        return livello;
    }
    @Override
    public int getBonusAttacco() {
        return 0;
    }
    @Override
    public int getBonusDifesa() {
        return protezione;
    }
    @Override
    public String getDescrizione() {
        return "La bolla rosa di Iron Heart diventa più grande e aumenta la difesa.";
    }
    @Override
    public void potenzia() {
        livello++;
        protezione += 10;
    }
}