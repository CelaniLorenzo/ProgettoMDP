package it.unicam.cs.mpgc.rpg130836.model.stato;

import java.io.IOException;

public interface ArchivioStatoGioco {

    void salva(StatoGioco statoGioco, String percorso) throws IOException;

    StatoGioco carica(String percorso) throws IOException;
}