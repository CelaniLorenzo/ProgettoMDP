package it.unicam.cs.mpgc.rpg130836;

public abstract class Nemico extends Personaggio implements Potenziabile {

    protected Nemico(String nome, int vitaMassima, int attaccoBase, int difesaBase) {
        super(nome, vitaMassima, attaccoBase, difesaBase);
    }
}