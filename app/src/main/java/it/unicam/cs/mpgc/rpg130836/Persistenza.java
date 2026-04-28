package it.unicam.cs.mpgc.rpg130836;

public interface Persistenza {
    void salva(StatoGioco statoGioco);
    StatoGioco carica();
}
