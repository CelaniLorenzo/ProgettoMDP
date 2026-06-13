package it.unicam.cs.mpgc.rpg130836.grafica.Avvio;

import java.util.Objects;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Image icona = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("/images/iron_man.png")
        ));
        stage.getIcons().add(icona);

        stage.setTitle("Iron Man e i suoi fantastici amici");

        new RpgJavaFxFrame(stage).mostraMenu();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
