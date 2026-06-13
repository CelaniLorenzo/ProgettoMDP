package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import java.util.Set;
import javafx.scene.input.KeyCode;

public class GestoreMovimentoEroe {

    private double eroeX = 380;
    private double eroeY = 280;

    private static final double VELOCITA_EROE_PIXEL_AL_SECONDO = 220.0;
    private static final double PASSO_TASTO_SINGOLO = 3.2;

    /*
     * Restituisce l'eroe x.
     */
    public double getEroeX() {
        return eroeX;
    }

    /*
     * Restituisce l'eroe y.
     */
    public double getEroeY() {
        return eroeY;
    }

    /*
     * Imposta il posizione.
     */
    public void impostaPosizione(double x, double y) {
        this.eroeX = x;
        this.eroeY = y;
    }

    /*
     * Muove l'elemento gestito.
     */
    public void muovi(Set<KeyCode> tastiPremuti) {
        muovi(tastiPremuti, 1.0 / 60.0);
    }

    /*
     * Muove l'elemento gestito.
     */
    public void muovi(Set<KeyCode> tastiPremuti, double deltaSecondi) {
        double movimentoX = 0;
        double movimentoY = 0;

        if (tastiPremuti.contains(KeyCode.UP) || tastiPremuti.contains(KeyCode.W)) {
            movimentoY--;
        }

        if (tastiPremuti.contains(KeyCode.DOWN) || tastiPremuti.contains(KeyCode.S)) {
            movimentoY++;
        }

        if (tastiPremuti.contains(KeyCode.LEFT) || tastiPremuti.contains(KeyCode.A)) {
            movimentoX--;
        }

        if (tastiPremuti.contains(KeyCode.RIGHT) || tastiPremuti.contains(KeyCode.D)) {
            movimentoX++;
        }

        if (movimentoX != 0 && movimentoY != 0) {
            movimentoX *= 0.7071;
            movimentoY *= 0.7071;
        }

        eroeX += movimentoX * VELOCITA_EROE_PIXEL_AL_SECONDO * deltaSecondi;
        eroeY += movimentoY * VELOCITA_EROE_PIXEL_AL_SECONDO * deltaSecondi;
        limitaPosizione();
    }

    /*
     * Muove l'elemento gestito.
     */
    public void muovi(String direzione) {
        switch (direzione) {
            case "UP" -> eroeY -= PASSO_TASTO_SINGOLO;
            case "DOWN" -> eroeY += PASSO_TASTO_SINGOLO;
            case "LEFT" -> eroeX -= PASSO_TASTO_SINGOLO;
            case "RIGHT" -> eroeX += PASSO_TASTO_SINGOLO;
            default -> {
            }
        }

        limitaPosizione();
    }

    /*
     * Limita la posizione entro i bordi della mappa.
     */
    private void limitaPosizione() {
        eroeX = Math.max(0, Math.min(760, eroeX));
        eroeY = Math.max(0, Math.min(560, eroeY));
    }
}
