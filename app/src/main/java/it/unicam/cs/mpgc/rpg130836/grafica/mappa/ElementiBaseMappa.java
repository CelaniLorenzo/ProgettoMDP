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

    public ElementiBaseMappa(Pane mappaPane,
                             ImageView sfondoMappa,
                             Label istruzioni,
                             Button tornaGioco) {
        this.mappaPane = mappaPane;
        this.sfondoMappa = sfondoMappa;
        this.istruzioni = istruzioni;
        this.tornaGioco = tornaGioco;
    }

    public Pane getMappaPane() {
        return mappaPane;
    }

    public ImageView getSfondoMappa() {
        return sfondoMappa;
    }

    public Label getIstruzioni() {
        return istruzioni;
    }

    public Button getTornaGioco() {
        return tornaGioco;
    }
}