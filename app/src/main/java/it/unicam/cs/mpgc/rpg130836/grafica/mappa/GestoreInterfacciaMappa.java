package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class GestoreInterfacciaMappa {

    /*
     * Crea il log della mappa.
     */
    public TextArea creaLogMappa() {
        TextArea logMappa = new TextArea();

        logMappa.setEditable(false);
        logMappa.setWrapText(true);
        logMappa.setPrefSize(300, 120);
        logMappa.setLayoutX(480);
        logMappa.setLayoutY(20);

        logMappa.setText(
                "Scegli un eroe sulla mappa, poi scegli un nemico.\n"
        );

        return logMappa;
    }

    /*
     * Crea il pulsante per combattere.
     */
    public Button creaPulsanteCombatti(Runnable azione) {
        Button button = new Button("Combatti turno");

        button.setLayoutX(620);
        button.setLayoutY(450);
        button.setDisable(true);

        button.setOnAction(e -> azione.run());

        return button;
    }
}