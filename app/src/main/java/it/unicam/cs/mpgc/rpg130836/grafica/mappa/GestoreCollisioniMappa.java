package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import java.util.List;
import javafx.scene.image.ImageView;

public class GestoreCollisioniMappa {

    private static final double DISTANZA_COLLISIONE = 35;

    public Nemico trovaNemicoInCollisione(
            ImageView eroeMappa,
            List<ImageView> nemiciMappa,
            List<Nemico> nemiciAssociatiMappa
    ) {
        if (eroeMappa == null) {
            return null;
        }

        for (int i = 0; i < nemiciMappa.size(); i++) {
            ImageView nemicoView = nemiciMappa.get(i);

            double distanzaX = eroeMappa.getLayoutX() - nemicoView.getLayoutX();
            double distanzaY = eroeMappa.getLayoutY() - nemicoView.getLayoutY();

            double distanza = Math.sqrt(
                    distanzaX * distanzaX + distanzaY * distanzaY
            );

            if (distanza < DISTANZA_COLLISIONE) {
                return nemiciAssociatiMappa.get(i);
            }
        }

        return null;
    }
}