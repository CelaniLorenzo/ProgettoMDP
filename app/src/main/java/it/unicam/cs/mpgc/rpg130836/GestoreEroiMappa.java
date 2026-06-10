package it.unicam.cs.mpgc.rpg130836;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.Objects;

public class GestoreEroiMappa {

    private final RisorseImmagini risorseImmagini;
    private final SchermataMappa schermataMappa;

    public GestoreEroiMappa(
            RisorseImmagini risorseImmagini,
            SchermataMappa schermataMappa
    ) {
        this.risorseImmagini = risorseImmagini;
        this.schermataMappa = schermataMappa;
    }

    public void aggiungiEroiAllaMappa(
            Pane mappaPane,
            List<Eroe> eroiDisponibili,
            List<ImageView> eroiMappa,
            List<Eroe> eroiAssociatiMappa,
            SelezioneEroeMappa selezioneEroeMappa
    ) {
        eroiMappa.clear();
        eroiAssociatiMappa.clear();

        double[][] posizioni = {
                {330, 430},
                {410, 430},
                {490, 430}
        };

        for (int i = 0; i < eroiDisponibili.size(); i++) {
            Eroe eroe = eroiDisponibili.get(i);

            Image immagineEroe = new Image(
                    Objects.requireNonNull(
                            getClass().getResource(
                                    risorseImmagini.percorsoEroe(eroe)
                            )
                    ).toExternalForm()
            );

            ImageView eroeView = schermataMappa.creaSprite(
                    immagineEroe,
                    posizioni[i][0],
                    posizioni[i][1],
                    70,
                    70
            );

            Label nomeEroe = new Label(eroe.getNome());
            nomeEroe.setStyle(
                    "-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: white;"
            );
            nomeEroe.setLayoutX(posizioni[i][0] - 5);
            nomeEroe.setLayoutY(posizioni[i][1] - 22);

            eroeView.setOnMouseClicked(
                    e -> selezioneEroeMappa.seleziona(eroe, eroeView)
            );

            nomeEroe.setOnMouseClicked(
                    e -> selezioneEroeMappa.seleziona(eroe, eroeView)
            );

            eroiMappa.add(eroeView);
            eroiAssociatiMappa.add(eroe);

            mappaPane.getChildren().addAll(nomeEroe, eroeView);
        }
    }
}