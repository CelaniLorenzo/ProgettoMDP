package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ElementiBaseMappa {

    private final Pane mappaPane;
    private final ImageView sfondoMappa;
    private final Label istruzioni;
    private final Button tornaGioco;

    /*
     * Costruisce un'istanza di ElementiBaseMappa.
     */
    public ElementiBaseMappa(Pane mappaPane,
                             ImageView sfondoMappa,
                             Label istruzioni,
                             Button tornaGioco) {
        this.mappaPane = mappaPane;
        this.sfondoMappa = sfondoMappa;
        this.istruzioni = istruzioni;
        this.tornaGioco = tornaGioco;
    }

    /*
     * Restituisce il pannello della mappa.
     */
    public Pane getMappaPane() {
        return mappaPane;
    }

    /*
     * Restituisce lo sfondo della mappa.
     */
    public ImageView getSfondoMappa() {
        return sfondoMappa;
    }

    /*
     * Restituisce l'istruzioni.
     */
    public Label getIstruzioni() {
        return istruzioni;
    }

    /*
     * Restituisce il pulsante per tornare al gioco.
     */
    public Button getTornaGioco() {
        return tornaGioco;
    }
}