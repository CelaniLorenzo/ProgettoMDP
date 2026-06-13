package it.unicam.cs.mpgc.rpg130836.grafica.menu;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SchermataMenu {

    /*
     * Mostra la schermata.
     */
    public void mostra(
            Stage stage,
            Runnable nuovaPartita,
            Runnable caricaPartita,
            Runnable esci
    ) {
        Button nuovaPartitaButton = new Button("Nuova Partita");
        Button caricaPartitaButton = new Button("Carica Partita");
        Button esciButton = new Button("Esci");

        String stileBottoneMenu = """
-fx-font-size: 22px;
-fx-font-weight: bold;
-fx-text-fill: black;
-fx-background-color: transparent;
-fx-border-color: transparent;
-fx-padding: 8 20 8 20;
-fx-focus-color: transparent;
-fx-faint-focus-color: transparent;
""";

        nuovaPartitaButton.setStyle(stileBottoneMenu);
        caricaPartitaButton.setStyle(stileBottoneMenu);
        esciButton.setStyle(stileBottoneMenu);

        nuovaPartitaButton.setPrefWidth(220);
        caricaPartitaButton.setPrefWidth(220);
        esciButton.setPrefWidth(220);

        nuovaPartitaButton.setOnAction(e -> nuovaPartita.run());
        caricaPartitaButton.setOnAction(e -> caricaPartita.run());
        esciButton.setOnAction(e -> esci.run());

        VBox menuBox = new VBox(15);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setTranslateY(-80);
        menuBox.setTranslateX(-30);
        menuBox.getChildren().addAll(
                nuovaPartitaButton,
                caricaPartitaButton,
                esciButton
        );

        StackPane root = new StackPane();

        Image sfondoMenu = new Image(
                getClass().getResource("/images/sfondo_menu.png").toExternalForm()
        );

        BackgroundSize dimensioniSfondo = new BackgroundSize(
                BackgroundSize.AUTO,
                BackgroundSize.AUTO,
                false,
                false,
                false,
                true
        );

        root.setBackground(new Background(new BackgroundImage(
                sfondoMenu,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                dimensioniSfondo
        )));

        root.getChildren().add(menuBox);

        Scene scene = new Scene(root, 620, 800);

        stage.setTitle("Iron Man e i suoi fantastici amici");
        stage.setScene(scene);
        stage.show();
    }
}