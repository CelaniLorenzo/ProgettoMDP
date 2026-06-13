package it.unicam.cs.mpgc.rpg130836.persistenza;

import java.io.IOException;

public interface Persistenza<T> {

    void salva(T oggetto, String percorso) throws IOException;

    T carica(String percorso) throws IOException;
}