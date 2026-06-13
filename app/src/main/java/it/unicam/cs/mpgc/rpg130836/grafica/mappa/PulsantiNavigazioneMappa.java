package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import javafx.scene.control.Button;

public class PulsantiNavigazioneMappa {

    private final Button mappaIndietro;
    private final Button mappaAvanti;

    /*
     * Costruisce un'istanza di PulsantiNavigazioneMappa.
     */
    public PulsantiNavigazioneMappa(Button mappaIndietro, Button mappaAvanti) {
        this.mappaIndietro = mappaIndietro;
        this.mappaAvanti = mappaAvanti;
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