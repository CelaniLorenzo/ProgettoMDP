package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GestoreRimozioneNemiciMappa {

    public void rimuoviNemicoSconfitto(Pane mappaPane,
                                       Nemico nemicoAttuale,
                                       List<ImageView> nemiciMappa,
                                       List<Label> nomiNemiciMappa,
                                       List<Nemico> nemiciAssociatiMappa) {

        if (mappaPane == null || nemicoAttuale == null) {
            return;
        }

        int indice = nemiciAssociatiMappa.indexOf(nemicoAttuale);

        if (indice == -1) {
            return;
        }

        ImageView nemicoView = nemiciMappa.get(indice);
        Label nomeNemico = nomiNemiciMappa.get(indice);

        mappaPane.getChildren().removeAll(nemicoView, nomeNemico);

        nemiciMappa.remove(indice);
        nomiNemiciMappa.remove(indice);
        nemiciAssociatiMappa.remove(indice);
    }
}