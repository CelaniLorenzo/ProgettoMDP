package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import java.util.List;
import javafx.scene.image.ImageView;

public class GestoreInizializzazioneMappa {

    public GestoreIncontriMappa creaGestoreIncontriMappa() {
        return new GestoreIncontriMappa();
    }

    public GestoreMovimentoNemiciMappa creaGestoreMovimentoNemiciMappa(
            List<ImageView> nemiciMappa,
            List<Nemico> nemiciAssociatiMappa
    ) {
        return new GestoreMovimentoNemiciMappa(
                nemiciMappa,
                nemiciAssociatiMappa
        );
    }
}