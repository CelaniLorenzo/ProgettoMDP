package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import java.util.Objects;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SchermataMappa {

    public Pane creaPaneMappa(Stage stage) {
        Pane mappaPane = new Pane();
        mappaPane.setPrefSize(800, 600);
        mappaPane.setFocusTraversable(true);

        return mappaPane;
    }
    public ImageView creaSfondo(String percorsoMappa) {

        Image immagineMappa = new Image(
                Objects.requireNonNull(
                        getClass().getResource(percorsoMappa)
                ).toExternalForm()
        );

        ImageView sfondoMappa = new ImageView(immagineMappa);
        sfondoMappa.setFitWidth(800);
        sfondoMappa.setFitHeight(600);
        sfondoMappa.setPreserveRatio(false);

        return sfondoMappa;
    }
    public Label creaIstruzioni() {
        Label istruzioni = new Label("Usa le frecce per muovere il personaggio");
        istruzioni.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;");
        istruzioni.setLayoutX(20);
        istruzioni.setLayoutY(20);

        return istruzioni;
    }
    public Button creaPulsanteTornaMenu(Runnable azione) {

        Button tornaMenu = new Button("Torna al menu");

        tornaMenu.setLayoutX(20);
        tornaMenu.setLayoutY(550);

        tornaMenu.setOnAction(e -> azione.run());

        return tornaMenu;
    }
    public ImageView creaSprite(
            Image immagine,
            double x,
            double y,
            double larghezza,
            double altezza
    ) {
        ImageView view = new ImageView(immagine);

        view.setFitWidth(larghezza);
        view.setFitHeight(altezza);
        view.setPreserveRatio(true);

        view.setLayoutX(x);
        view.setLayoutY(y);

        return view;
    }
}