package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import java.util.List;
import java.util.Objects;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GestoreNemiciVisualiMappa {

    private final RisorseImmagini risorseImmagini;
    private final SchermataMappa schermataMappa;

    /*
     * Costruisce un'istanza di GestoreNemiciVisualiMappa.
     */
    public GestoreNemiciVisualiMappa(
            RisorseImmagini risorseImmagini,
            SchermataMappa schermataMappa
    ) {
        this.risorseImmagini = risorseImmagini;
        this.schermataMappa = schermataMappa;
    }

    /*
     * Aggiunge i nemici alla mappa.
     */
    public void aggiungiNemiciAllaMappa(
            Pane mappaPane,
            List<Nemico> nemiciDisponibili,
            List<ImageView> nemiciMappa,
            List<Label> nomiNemiciMappa,
            List<Nemico> nemiciAssociatiMappa,
            SelezioneNemicoMappa selezioneNemicoMappa
    ) {
        nemiciMappa.clear();
        nomiNemiciMappa.clear();
        nemiciAssociatiMappa.clear();

        double[][] posizioni = {
                {150, 420},
                {600, 360},
                {700, 170}
        };

        for (int i = 0; i < nemiciDisponibili.size(); i++) {
            Nemico nemico = nemiciDisponibili.get(i);

            if (!nemico.isVivo()) {
                continue;
            }

            Image immagineNemico = new Image(
                    Objects.requireNonNull(
                            getClass().getResource(
                                    risorseImmagini.percorsoNemico(nemico)
                            )
                    ).toExternalForm()
            );

            ImageView nemicoView = schermataMappa.creaSprite(
                    immagineNemico,
                    posizioni[i][0],
                    posizioni[i][1],
                    60,
                    60
            );

            Label nomeNemico = new Label(nemico.getNome());
            nomeNemico.setStyle(
                    "-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: white;"
            );
            nomeNemico.setLayoutX(posizioni[i][0] - 10);
            nomeNemico.setLayoutY(posizioni[i][1] - 25);

            nemicoView.setOnMouseClicked(e -> selezioneNemicoMappa.seleziona(nemico));
            nomeNemico.setOnMouseClicked(e -> selezioneNemicoMappa.seleziona(nemico));

            nemiciMappa.add(nemicoView);
            nomiNemiciMappa.add(nomeNemico);
            nemiciAssociatiMappa.add(nemico);

            mappaPane.getChildren().addAll(nomeNemico, nemicoView);
        }
    }
}