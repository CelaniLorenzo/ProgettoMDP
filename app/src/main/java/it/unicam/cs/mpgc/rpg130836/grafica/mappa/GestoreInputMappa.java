package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import java.util.HashSet;
import java.util.Set;
import javafx.animation.AnimationTimer;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.Scene;

public class GestoreInputMappa {

    public void configuraMovimentoEroe(Scene scene,
                                       ImageView eroeMappa,
                                       TextArea logMappa,
                                       GestoreMovimentoEroe gestoreMovimentoEroe,
                                       Runnable controllaCollisione) {

        Set<KeyCode> tastiPremuti = new HashSet<>();

        AnimationTimer movimentoEroe = new AnimationTimer() {

            private long ultimoFrame;

            @Override
            public void handle(long now) {
                if (tastiPremuti.isEmpty()) {
                    ultimoFrame = 0;
                    stop();
                    return;
                }

                if (eroeMappa == null) {
                    return;
                }

                double deltaSecondi = calcolaDeltaSecondi(now);

                gestoreMovimentoEroe.muovi(tastiPremuti, deltaSecondi);

                eroeMappa.setLayoutX(gestoreMovimentoEroe.getEroeX());
                eroeMappa.setLayoutY(gestoreMovimentoEroe.getEroeY());

                controllaCollisione.run();
            }

            private double calcolaDeltaSecondi(long now) {
                if (ultimoFrame == 0) {
                    ultimoFrame = now;
                    return 1.0 / 60.0;
                }

                double deltaSecondi =
                        (now - ultimoFrame) / 1_000_000_000.0;

                ultimoFrame = now;
                return Math.min(deltaSecondi, 0.033);
            }
        };

        scene.setOnKeyPressed(e -> {

            if (eroeMappa == null) {
                if (logMappa != null) {
                    logMappa.appendText("Devi prima selezionare un eroe sulla mappa.\n");
                }
                return;
            }

            tastiPremuti.add(e.getCode());
            movimentoEroe.start();
        });

        scene.setOnKeyReleased(e -> {
            tastiPremuti.remove(e.getCode());
        });
    }
}
