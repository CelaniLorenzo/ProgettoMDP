package it.unicam.cs.mpgc.rpg130836;

public class Gioco {

    public void avvia() {

        Personaggio[] squadra = {
                new Fulmine(),
                new Fuoco(),
                new Ghiaccio()
        };

        Reattore reattore = new Reattore();

        Combattimento c = new Combattimento();
        c.avvia(squadra, reattore);
    }
}