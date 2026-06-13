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

    /*
     * Costruisce un'istanza di DatiAperturaMappa.
     */
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

    /*
     * Restituisce il pulsante per tornare alla mappa precedente.
     */
    public Button getMappaIndietro() {
        return mappaIndietro;
    }

    /*
     * Restituisce il pulsante per passare alla mappa successiva.
     */
    public Button getMappaAvanti() {
        return mappaAvanti;
    }
}