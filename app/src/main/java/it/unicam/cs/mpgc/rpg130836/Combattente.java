package it.unicam.cs.mpgc.rpg130836;

public interface Combattente {

    String getNome();

    int getVita();

    int getVitaMassima();

    boolean isVivo();

    int attacca(Combattente bersaglio);

    void riceviDanno(int danno);

    int calcolaAttacco();

    int calcolaDifesa();
}
