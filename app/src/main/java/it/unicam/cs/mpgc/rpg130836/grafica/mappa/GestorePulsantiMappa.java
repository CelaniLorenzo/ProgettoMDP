package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import java.util.Objects;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GestorePulsantiMappa {

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

    /*
     * Crea il pulsante dell'attacco di squadra sulla mappa.
     */
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

    /*
     * Crea il pulsante di potenziamento sulla mappa.
     */
    public Button creaPulsantePotenziaMappa(Runnable azione) {
        Button button = new Button("Potenzia vincitore");
        button.setLayoutX(620);
        button.setLayoutY(545);
        button.setDisable(true);
        button.setOnAction(e -> azione.run());
        return button;
    }

    /*
     * Crea il pulsante per passare alla mappa successiva.
     */
    public Button creaPulsanteMappaAvanti(Runnable azione) {
        Button button = new Button("Mappa avanti");
        button.setLayoutX(270);
        button.setLayoutY(550);
        button.setOnAction(e -> azione.run());
        return button;
    }

    /*
     * Crea il pulsante per tornare alla mappa precedente.
     */
    public Button creaPulsanteMappaIndietro(Runnable azione) {
        Button button = new Button("Mappa indietro");
        button.setLayoutX(140);
        button.setLayoutY(550);
        button.setOnAction(e -> azione.run());
        return button;
    }

    /*
     * Abilita i pulsanti combattimento mappa.
     */
    public void abilitaPulsantiCombattimentoMappa(Button combattiButton,
                                                  Button attaccoSquadraButton,
                                                  Button potenziaButton) {
        if (combattiButton != null) {
            combattiButton.setDisable(false);
        }

        if (attaccoSquadraButton != null) {
            attaccoSquadraButton.setDisable(false);
        }

        if (potenziaButton != null) {
            potenziaButton.setDisable(true);
        }
    }
}