package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class GestoreLayoutMappa {

    /*
     * Aggiunge l'elementi.
     */
    public void aggiungiElementi(Pane mappaPane, Node... elementi) {
        for (Node elemento : elementi) {
            mappaPane.getChildren().add(elemento);
        }
    }
}
