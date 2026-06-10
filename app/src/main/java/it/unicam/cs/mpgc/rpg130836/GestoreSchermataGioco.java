package it.unicam.cs.mpgc.rpg130836;

import javafx.scene.layout.HBox;
import javafx.scene.control.Button;

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
}