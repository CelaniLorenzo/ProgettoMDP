package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import java.util.function.DoubleConsumer;
import javafx.animation.AnimationTimer;

public class GestoreNemiciMappa {

    private AnimationTimer movimentoNemiciTimer;

    /*
     * Ferma il movimento dei nemici.
     */
    public void fermaMovimentoNemici() {
        if (movimentoNemiciTimer != null) {
            movimentoNemiciTimer.stop();
        }
    }

    /*
     * Avvia il movimento dei nemici.
     */
    public void avviaMovimentoNemici(Runnable azioneMovimento) {
        avviaMovimentoNemici(deltaSecondi -> azioneMovimento.run());
    }

    /*
     * Avvia il movimento dei nemici.
     */
    public void avviaMovimentoNemici(DoubleConsumer azioneMovimento) {
        fermaMovimentoNemici();

        movimentoNemiciTimer = new AnimationTimer() {

            private long ultimoFrame;

            /*
             * Gestisce l'aggiornamento del timer o dell'evento ricevuto.
             */
            @Override
            public void handle(long now) {
                double deltaSecondi = calcolaDeltaSecondi(now);

                azioneMovimento.accept(deltaSecondi);
            }

            /*
             * Calcola il tempo trascorso in secondi.
             */
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
