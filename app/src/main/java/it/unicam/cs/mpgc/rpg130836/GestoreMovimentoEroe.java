package it.unicam.cs.mpgc.rpg130836;

public class GestoreMovimentoEroe {

    private double eroeX = 380;
    private double eroeY = 280;

    private static final double VELOCITA_EROE = 10;

    public double getEroeX() {
        return eroeX;
    }

    public double getEroeY() {
        return eroeY;
    }

    public void impostaPosizione(double x, double y) {
        this.eroeX = x;
        this.eroeY = y;
    }
    public void muovi(String direzione) {
        switch (direzione) {
            case "UP" -> eroeY -= VELOCITA_EROE;
            case "DOWN" -> eroeY += VELOCITA_EROE;
            case "LEFT" -> eroeX -= VELOCITA_EROE;
            case "RIGHT" -> eroeX += VELOCITA_EROE;
            default -> {
            }
        }

        eroeX = Math.max(0, Math.min(760, eroeX));
        eroeY = Math.max(0, Math.min(560, eroeY));
    }

}