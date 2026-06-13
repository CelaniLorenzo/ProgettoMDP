package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;

@FunctionalInterface
public interface SelezioneNemicoMappa {
    void seleziona(Nemico nemico);
}