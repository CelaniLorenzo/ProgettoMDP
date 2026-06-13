package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CreatoreElementiBaseMappa {

    private final SchermataMappa schermataMappa;

    /*
     * Costruisce un'istanza di CreatoreElementiBaseMappa.
     */
    public CreatoreElementiBaseMappa(SchermataMappa schermataMappa) {
        this.schermataMappa = schermataMappa;
    }

    /*
     * Crea gli elementi richiesti.
     */
    public ElementiBaseMappa crea(Stage stage,
                                  String immagineMappa,
                                  Runnable azioneTornaGioco) {

        Pane mappaPane = schermataMappa.creaPaneMappa(stage);
        ImageView sfondoMappa = schermataMappa.creaSfondo(immagineMappa);
        Label istruzioni = schermataMappa.creaIstruzioni();
        Button tornaGioco = schermataMappa.creaPulsanteTornaMenu(azioneTornaGioco);

        return new ElementiBaseMappa(
                mappaPane,
                sfondoMappa,
                istruzioni,
                tornaGioco
        );
    }
}