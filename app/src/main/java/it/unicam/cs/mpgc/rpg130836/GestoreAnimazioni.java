package it.unicam.cs.mpgc.rpg130836;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Objects;
public class GestoreAnimazioni {
    public void lanciaPotere(
            Pane mappaPane,
            ImageView eroeView,
            ImageView nemicoView,
            String percorsoImmaginePotere
    ) {
        Image immaginePotere = new Image(
                Objects.requireNonNull(
                        getClass().getResource(percorsoImmaginePotere)
                ).toExternalForm()
        );

        ImageView potereView = new ImageView(immaginePotere);
        potereView.setFitWidth(45);
        potereView.setFitHeight(45);
        potereView.setPreserveRatio(true);

        potereView.setLayoutX(eroeView.getLayoutX() + 30);
        potereView.setLayoutY(eroeView.getLayoutY() + 20);

        mappaPane.getChildren().add(potereView);
        potereView.toFront();

        TranslateTransition animazione = new TranslateTransition(Duration.millis(500), potereView);
        animazione.setToX(nemicoView.getLayoutX() - eroeView.getLayoutX());
        animazione.setToY(nemicoView.getLayoutY() - eroeView.getLayoutY());

        animazione.setOnFinished(e -> mappaPane.getChildren().remove(potereView));
        animazione.play();
    }
}
