package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import it.unicam.cs.mpgc.rpg130836.model.abilita.Reattore;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javafx.scene.image.ImageView;

public class GestoreMovimentoNemiciMappa {

    private static final double VELOCITA_NEMICO_PIXEL_AL_SECONDO = 70.0;
    private static final double LIMITE_X_MIN = 40;
    private static final double LIMITE_Y_MIN = 80;
    private static final double LIMITE_X_MAX = 720;
    private static final double LIMITE_Y_MAX = 520;
    private static final long INTERVALLO_CAMBIO_DIREZIONE = 900_000_000L;

    private final List<ImageView> nemiciMappa;
    private final List<Nemico> nemiciAssociatiMappa;
    private final Map<ImageView, DirezioneMovimento> direzioni;
    private final Random random;
    private long ultimoCambioDirezione;

    /*
     * Costruisce un'istanza di GestoreMovimentoNemiciMappa.
     */
    public GestoreMovimentoNemiciMappa(List<ImageView> nemiciMappa,
                                       List<Nemico> nemiciAssociatiMappa) {
        this.nemiciMappa = nemiciMappa;
        this.nemiciAssociatiMappa = nemiciAssociatiMappa;
        this.direzioni = new IdentityHashMap<>();
        this.random = new Random();
    }

    /*
     * Muove i nemici.
     */
    public void muoviNemici() {
        muoviNemici(1.0 / 60.0);
    }

    /*
     * Muove i nemici.
     */
    public void muoviNemici(double deltaSecondi) {
        aggiornaDirezioniSeNecessario();

        for (int i = 0; i < nemiciMappa.size(); i++) {
            ImageView nemicoView = nemiciMappa.get(i);
            Nemico nemico = nemiciAssociatiMappa.get(i);

            if (nemico.getNome().equals("Reattore")) {
                continue;
            }

            DirezioneMovimento direzione =
                    direzioni.computeIfAbsent(
                            nemicoView,
                            view -> creaDirezioneCasuale()
            );

            double nuovaX =
                    nemicoView.getLayoutX()
                            + direzione.x * VELOCITA_NEMICO_PIXEL_AL_SECONDO * deltaSecondi;

            double nuovaY =
                    nemicoView.getLayoutY()
                            + direzione.y * VELOCITA_NEMICO_PIXEL_AL_SECONDO * deltaSecondi;

            if (nuovaX < LIMITE_X_MIN || nuovaX > LIMITE_X_MAX) {
                direzione.invertiX();
                nuovaX = limita(nuovaX, LIMITE_X_MIN, LIMITE_X_MAX);
            }

            if (nuovaY < LIMITE_Y_MIN || nuovaY > LIMITE_Y_MAX) {
                direzione.invertiY();
                nuovaY = limita(nuovaY, LIMITE_Y_MIN, LIMITE_Y_MAX);
            }

            nemicoView.setLayoutX(nuovaX);
            nemicoView.setLayoutY(nuovaY);
        }
    }

    /*
     * Aggiorna le direzioni se necessario.
     */
    private void aggiornaDirezioniSeNecessario() {
        direzioni.keySet().retainAll(nemiciMappa);

        long ora = System.nanoTime();

        if (ora - ultimoCambioDirezione < INTERVALLO_CAMBIO_DIREZIONE) {
            return;
        }

        ultimoCambioDirezione = ora;

        for (ImageView nemicoView : nemiciMappa) {
            direzioni.put(nemicoView, creaDirezioneCasuale());
        }
    }

    /*
     * Crea una direzione casuale.
     */
    private DirezioneMovimento creaDirezioneCasuale() {
        double x = random.nextDouble() * 2 - 1;
        double y = random.nextDouble() * 2 - 1;
        double lunghezza = Math.sqrt(x * x + y * y);

        if (lunghezza == 0) {
            return new DirezioneMovimento(1, 0);
        }

        return new DirezioneMovimento(x / lunghezza, y / lunghezza);
    }

    /*
     * Limita un valore tra il minimo e il massimo indicati.
     */
    private double limita(double valore, double minimo, double massimo) {
        return Math.max(minimo, Math.min(massimo, valore));
    }

    private static class DirezioneMovimento {

        private double x;
        private double y;

        DirezioneMovimento(double x, double y) {
            this.x = x;
            this.y = y;
        }

        /*
         * Inverte la direzione sull'asse X.
         */
        void invertiX() {
            x = -x;
        }

        /*
         * Inverte la direzione sull'asse Y.
         */
        void invertiY() {
            y = -y;
        }
    }
}
