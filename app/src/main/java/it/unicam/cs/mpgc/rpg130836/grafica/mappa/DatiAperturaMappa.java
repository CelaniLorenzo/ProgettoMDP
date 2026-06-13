package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class DatiAperturaMappa {

    private final Pane mappaPane;
    private final ImageView sfondoMappa;
    private final Label istruzioni;
    private final Button tornaGioco;
    private final Button mappaIndietro;
    private final Button mappaAvanti;

    public DatiAperturaMappa(
            Pane mappaPane,
            ImageView sfondoMappa,
            Label istruzioni,
            Button tornaGioco,
            Button mappaIndietro,
            Button mappaAvanti
    ) {
        this.mappaPane = mappaPane;
        this.sfondoMappa = sfondoMappa;
        this.istruzioni = istruzioni;
        this.tornaGioco = tornaGioco;
        this.mappaIndietro = mappaIndietro;
        this.mappaAvanti = mappaAvanti;
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

    public Button getMappaIndietro() {
        return mappaIndietro;
    }

    public Button getMappaAvanti() {
        return mappaAvanti;
    }
}