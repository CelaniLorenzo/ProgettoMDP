package it.unicam.cs.mpgc.rpg130836.grafica.Animazioni;

import java.util.Objects;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class GestoreAnimazioni {

    /*
     * Lancia un'immagine di potere dall'eroe verso il nemico,
     * creando una piccola animazione che parte dalla posizione dell'eroe
     * e arriva alla posizione del nemico.
     */
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

    /*
     * Metodo di supporto che lancia il potere dell'eroe
     * verso il nemico attualmente selezionato.
     */
    public void lanciaPotereEroeVersoNemicoCorrente(
            Pane mappaPane,
            ImageView eroeView,
            ImageView nemicoView,
            String percorsoPotere
    ) {
        lanciaPotere(
                mappaPane,
                eroeView,
                nemicoView,
                percorsoPotere
        );
    }

    /*
     * Metodo di supporto che lancia un potere
     * verso il nemico attualmente selezionato.
     */
    public void lanciaPotereVersoNemicoCorrente(
            Pane mappaPane,
            ImageView eroeView,
            ImageView nemicoView,
            String percorsoPotere
    ) {
        lanciaPotere(
                mappaPane,
                eroeView,
                nemicoView,
                percorsoPotere
        );
    }
}