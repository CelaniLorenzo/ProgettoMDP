package it.unicam.cs.mpgc.rpg130836;

public class Ghiaccio extends Personaggio {

    public Ghiaccio() {
        super("Ghiaccio", 90, 12, 8);
    }

    @Override
    public void usaAbilita(Personaggio nemico) {
        nemico.difesa -= 3;
        System.out.println(nome + " riduce la difesa del nemico!");
    }
}