package it.unicam.cs.mpgc.rpg130836.persistenza;

import java.io.IOException;

public interface Persistenza<T> {

    /*
     * Salva i dati nel percorso indicato.
     */
    void salva(T oggetto, String percorso) throws IOException;

    /*
     * Carica i dati dal percorso indicato.
     */
    T carica(String percorso) throws IOException;
}