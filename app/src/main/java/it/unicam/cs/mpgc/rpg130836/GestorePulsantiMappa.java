package it.unicam.cs.mpgc.rpg130836;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;

public class GestorePulsantiMappa {

    public Button creaPulsanteCombatti(Runnable azione) {
        Button button = new Button("Combatti turno");

        button.setLayoutX(620);
        button.setLayoutY(450);
        button.setDisable(true);
        button.setOnAction(e -> azione.run());

        return button;
    }
    public Button creaPulsanteAttaccoSquadraMappa(Runnable azione) {
        Image immagineAttaccoSquadra = new Image(
                Objects.requireNonNull(
                        getClass().getResource("/images/attacco_di_squadra.png")
                ).toExternalForm()
        );

        ImageView iconaAttaccoSquadra = new ImageView(immagineAttaccoSquadra);
        iconaAttaccoSquadra.setFitWidth(120);
        iconaAttaccoSquadra.setFitHeight(40);
        iconaAttaccoSquadra.setPreserveRatio(true);

        Button button = new Button();
        button.setGraphic(iconaAttaccoSquadra);
        button.setLayoutX(620);
        button.setLayoutY(490);
        button.setDisable(true);
        button.setOnAction(e -> azione.run());

        return button;
    }
    public Button creaPulsantePotenziaMappa(Runnable azione) {
        Button button = new Button("Potenzia vincitore");
        button.setLayoutX(620);
        button.setLayoutY(545);
        button.setDisable(true);
        button.setOnAction(e -> azione.run());
        return button;
    }
    public Button creaPulsanteMappaAvanti(Runnable azione) {
        Button button = new Button("Mappa avanti");
        button.setLayoutX(270);
        button.setLayoutY(550);
        button.setOnAction(e -> azione.run());
        return button;
    }
    public Button creaPulsanteMappaIndietro(Runnable azione) {
        Button button = new Button("Mappa indietro");
        button.setLayoutX(140);
        button.setLayoutY(550);
        button.setOnAction(e -> azione.run());
        return button;
    }
}