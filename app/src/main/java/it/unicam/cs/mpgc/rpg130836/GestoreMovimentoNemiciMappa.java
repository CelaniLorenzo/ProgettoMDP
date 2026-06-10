package it.unicam.cs.mpgc.rpg130836;

import javafx.scene.image.ImageView;

import java.util.List;
import java.util.Random;

public class GestoreMovimentoNemiciMappa {

    private final List<ImageView> nemiciMappa;
    private final List<Nemico> nemiciAssociatiMappa;
    private final Random random;

    public GestoreMovimentoNemiciMappa(List<ImageView> nemiciMappa,
                                       List<Nemico> nemiciAssociatiMappa) {
        this.nemiciMappa = nemiciMappa;
        this.nemiciAssociatiMappa = nemiciAssociatiMappa;
        this.random = new Random();
    }

    public void muoviNemici() {
        for (int i = 0; i < nemiciMappa.size(); i++) {
            ImageView nemicoView = nemiciMappa.get(i);
            Nemico nemico = nemiciAssociatiMappa.get(i);

            if (nemico.getNome().equals("Reattore")) {
                continue;
            }

            double movimentoX = (random.nextInt(3) - 1) * 20;
            double movimentoY = (random.nextInt(3) - 1) * 20;

            double nuovaX = nemicoView.getLayoutX() + movimentoX;
            double nuovaY = nemicoView.getLayoutY() + movimentoY;

            if (nuovaX < 40) {
                nuovaX = 40;
            }

            if (nuovaY < 80) {
                nuovaY = 80;
            }

            if (nuovaX > 720) {
                nuovaX = 720;
            }

            if (nuovaY > 520) {
                nuovaY = 520;
            }

            nemicoView.setLayoutX(nuovaX);
            nemicoView.setLayoutY(nuovaY);
        }
    }
}