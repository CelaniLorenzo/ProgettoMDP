package it.unicam.cs.mpgc.rpg130836;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class GestoreNemiciMappa {

    private Timeline movimentoNemiciTimeline;

    public void fermaMovimentoNemici() {
        if (movimentoNemiciTimeline != null) {
            movimentoNemiciTimeline.stop();
        }
    }
    public void avviaMovimentoNemici(Runnable azioneMovimento) {
        fermaMovimentoNemici();

        movimentoNemiciTimeline = new Timeline(
                new KeyFrame(Duration.millis(700), e -> azioneMovimento.run())
        );

        movimentoNemiciTimeline.setCycleCount(Timeline.INDEFINITE);
        movimentoNemiciTimeline.play();
    }
}