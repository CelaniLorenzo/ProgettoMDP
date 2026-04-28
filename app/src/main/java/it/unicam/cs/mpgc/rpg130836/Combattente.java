package it.unicam.cs.mpgc.rpg130836;

public interface Combattente {

    String getNome();

    boolean isVivo();

    int attacca(Personaggio bersaglio);

    int riceviDanno(int danno);
}