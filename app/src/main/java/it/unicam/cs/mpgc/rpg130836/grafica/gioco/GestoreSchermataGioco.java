package it.unicam.cs.mpgc.rpg130836.grafica.gioco;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GestoreSchermataGioco {

    private final SchermataGioco schermataGioco;

    public GestoreSchermataGioco(SchermataGioco schermataGioco) {
        this.schermataGioco = schermataGioco;
    }

    public HBox creaSceltaEroi(
            Eroe eroe1,
            Eroe eroe2,
            Eroe eroe3,
            Runnable azioneEroe1,
            Runnable azioneEroe2,
            Runnable azioneEroe3
    ) {
        return schermataGioco.creaSceltaEroi(
                schermataGioco.creaPulsanteEroe(eroe1, azioneEroe1),
                schermataGioco.creaPulsanteEroe(eroe2, azioneEroe2),
                schermataGioco.creaPulsanteEroe(eroe3, azioneEroe3)
        );
    }
    public void gestisciProssimoIncontro(
            boolean eroeSelezionato,
            int vitaNemicoAttuale,
            int numeroIncontro,
            int numeroNemici,
            Runnable azioneNuovoIncontro
    ) {
        if (!eroeSelezionato) {
            schermataGioco.getLogArea()
                    .appendText("Devi prima scegliere un eroe.\n");
            return;
        }

        if (vitaNemicoAttuale > 0) {
            schermataGioco.getLogArea()
                    .appendText("Devi prima sconfiggere il nemico attuale.\n");
            return;
        }

        if (numeroIncontro >= numeroNemici) {
            schermataGioco.getLogArea()
                    .appendText("Non ci sono altri nemici.\n");
            return;
        }

        azioneNuovoIncontro.run();
    }
    public Button creaAttaccoSquadraButton(Runnable azione) {
        Button button = new Button("Attacco di squadra");
        button.setOnAction(e -> azione.run());
        return button;
    }

    public Button creaPotenziaButton(Runnable azione) {
        Button button = new Button("Potenzia vincitore");
        button.setDisable(true);
        button.setOnAction(e -> azione.run());
        return button;
    }
    public void configuraLayoutGioco(
            Label scegliEroeLabel,
            HBox sceltaEroi,
            Button attaccoSquadraButton,
            Button potenziaButton
    ) {
        VBox root = schermataGioco.getRoot();

        root.getChildren().add(1, scegliEroeLabel);
        root.getChildren().add(2, sceltaEroi);

        root.getChildren().add(
                root.getChildren().indexOf(schermataGioco.getApriMappaButton()),
                attaccoSquadraButton
        );

        root.getChildren().add(
                root.getChildren().indexOf(schermataGioco.getApriMappaButton()),
                potenziaButton
        );
    }
    public Button creaAttaccoSquadra(
            Runnable azioneAttaccoSquadra
    ) {
        return creaAttaccoSquadraButton(azioneAttaccoSquadra);
    }

    public Button creaPotenzia(
            Runnable azionePotenziamento
    ) {
        return creaPotenziaButton(azionePotenziamento);
    }
    public void configuraPulsantiSchermataGioco(
            Stage stage,
            Runnable azioneProssimoIncontro,
            Runnable azioneAttacca,
            Runnable azioneSalva,
            Runnable azioneApriMappa,
            Runnable azioneTornaMenu
    ) {
        schermataGioco.getProssimoIncontroButton()
                .setOnAction(e -> azioneProssimoIncontro.run());

        schermataGioco.getAttaccaButton()
                .setOnAction(e -> azioneAttacca.run());

        schermataGioco.getSalvaButton()
                .setOnAction(e -> azioneSalva.run());

        schermataGioco.getApriMappaButton()
                .setOnAction(e -> azioneApriMappa.run());

        schermataGioco.getTornaMenuButton()
                .setOnAction(e -> azioneTornaMenu.run());
    }
}