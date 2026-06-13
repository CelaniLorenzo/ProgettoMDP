package it.unicam.cs.mpgc.rpg130836.model.stato;

import java.io.IOException;

public interface ArchivioStatoGioco {

    /*
     * Salva i dati nel percorso indicato.
     */
    void salva(StatoGioco statoGioco, String percorso) throws IOException;

    /*
     * Carica i dati dal percorso indicato.
     */
    StatoGioco carica(String percorso) throws IOException;
}