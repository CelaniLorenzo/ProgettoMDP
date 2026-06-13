package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import javafx.scene.image.ImageView;

@FunctionalInterface
public interface SelezioneEroeMappa {

    /*
     * Seleziona l'elemento indicato.
     */
    void seleziona(Eroe eroe, ImageView eroeView);
}