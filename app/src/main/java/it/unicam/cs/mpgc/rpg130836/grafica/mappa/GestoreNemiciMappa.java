package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import java.util.function.DoubleConsumer;
import javafx.animation.AnimationTimer;

public class GestoreNemiciMappa {

    private AnimationTimer movimentoNemiciTimer;

    public void fermaMovimentoNemici() {
        if (movimentoNemiciTimer != null) {
            movimentoNemiciTimer.stop();
        }
    }

    public void avviaMovimentoNemici(Runnable azioneMovimento) {
        avviaMovimentoNemici(deltaSecondi -> azioneMovimento.run());
    }

    public void avviaMovimentoNemici(DoubleConsumer azioneMovimento) {
        fermaMovimentoNemici();

        movimentoNemiciTimer = new AnimationTimer() {

            private long ultimoFrame;

            @Override
            public void handle(long now) {
                double deltaSecondi = calcolaDeltaSecondi(now);

                azioneMovimento.accept(deltaSecondi);
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

        movimentoNemiciTimer.start();
    }
}
