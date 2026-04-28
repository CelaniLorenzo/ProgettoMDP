package it.unicam.cs.mpgc.rpg130836;

public interface AbilitaSpeciale extends Potenziabile {

    String getNome();

    int getLivello();

    int getBonusAttacco();

    int getBonusDifesa();

    String getDescrizione();
}
