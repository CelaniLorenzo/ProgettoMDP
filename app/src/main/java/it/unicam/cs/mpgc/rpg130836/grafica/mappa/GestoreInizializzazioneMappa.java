package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import java.util.List;
import javafx.scene.image.ImageView;

public class GestoreInizializzazioneMappa {

    /*
     * Crea il gestore degli incontri della mappa.
     */
    public GestoreIncontriMappa creaGestoreIncontriMappa() {
        return new GestoreIncontriMappa();
    }

    /*
     * Crea il gestore del movimento dei nemici sulla mappa.
     */
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