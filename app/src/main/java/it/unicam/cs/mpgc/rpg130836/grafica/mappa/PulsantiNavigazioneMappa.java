package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import javafx.scene.control.Button;

public class PulsantiNavigazioneMappa {

    private final Button mappaIndietro;
    private final Button mappaAvanti;

    public PulsantiNavigazioneMappa(Button mappaIndietro, Button mappaAvanti) {
        this.mappaIndietro = mappaIndietro;
        this.mappaAvanti = mappaAvanti;
    }

    public Button getMappaIndietro() {
        return mappaIndietro;
    }

    public Button getMappaAvanti() {
        return mappaAvanti;
    }
}