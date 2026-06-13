package it.unicam.cs.mpgc.rpg130836.grafica.gioco;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SchermataGioco {

    private Label nomeGiocatoreLabel;
    private Label vitaGiocatoreLabel;
    private Label nomeNemicoLabel;
    private Label vitaNemicoLabel;
    private TextArea logArea;
    private VBox root;
    private Button prossimoIncontroButton;
    private Button attaccaButton;
    private Button salvaButton;
    private Button apriMappaButton;
    private Button tornaMenuButton;

    public TextArea getLogArea() {
        return logArea;
    }

    public Label getNomeGiocatoreLabel() {
        return nomeGiocatoreLabel;
    }

    public Label getVitaGiocatoreLabel() {
        return vitaGiocatoreLabel;
    }

    public Label getNomeNemicoLabel() {
        return nomeNemicoLabel;
    }

    public Label getVitaNemicoLabel() {
        return vitaNemicoLabel;
    }
    public Button getProssimoIncontroButton() {
        return prossimoIncontroButton;
    }

    public Button getAttaccaButton() {
        return attaccaButton;
    }

    public Button getSalvaButton() {
        return salvaButton;
    }

    public Button getApriMappaButton() {
        return apriMappaButton;
    }

    public Button getTornaMenuButton() {
        return tornaMenuButton;
    }

    public void mostra(Stage stage, String nomeGiocatoreAttuale) {
        Label titolo = new Label("PARTITA IN CORSO");

        nomeGiocatoreLabel = new Label("Giocatore: " + nomeGiocatoreAttuale);
        vitaGiocatoreLabel = new Label("Vita: -");
        nomeNemicoLabel = new Label("Nemico: nessuno");
        vitaNemicoLabel = new Label("Vita: -");

        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setWrapText(true);
        logArea.setPrefHeight(250);
        logArea.setText("Partita iniziata.\n");
        prossimoIncontroButton = new Button("Prossimo incontro");
        attaccaButton = new Button("Combatti turno");
        salvaButton = new Button("Salva partita");
        apriMappaButton = new Button("Apri mappa");
        tornaMenuButton = new Button("Torna al menu");

        root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(
                titolo,
                nomeGiocatoreLabel,
                vitaGiocatoreLabel,
                nomeNemicoLabel,
                vitaNemicoLabel,
                logArea,
                prossimoIncontroButton,
                attaccaButton,
                apriMappaButton,
                salvaButton,
                tornaMenuButton
        );

        Scene scene = new Scene(root, 800, 500);
        stage.setScene(scene);
    }
    public Label creaTitolo() {
        return new Label("PARTITA IN CORSO");
    }
    public Button creaPulsanteEroe(Eroe eroe, Runnable azione) {
        Button bottone = new Button(eroe.getNome());
        bottone.setOnAction(e -> azione.run());
        return bottone;
    }
    public HBox creaSceltaEroi(Button eroe1, Button eroe2, Button eroe3) {
        HBox sceltaEroi = new HBox(10);
        sceltaEroi.setAlignment(Pos.CENTER);
        sceltaEroi.getChildren().addAll(eroe1, eroe2, eroe3);

        return sceltaEroi;
    }
    public Label creaEtichettaSceltaEroe() {
        return new Label("Scegli eroe:");
    }
    public Button creaPulsante(String testo, Runnable azione) {
        Button bottone = new Button(testo);
        bottone.setOnAction(e -> azione.run());
        return bottone;
    }
    public VBox getRoot() {
        return root;
    }
}