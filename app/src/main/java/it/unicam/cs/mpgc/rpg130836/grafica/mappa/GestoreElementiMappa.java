package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GestoreElementiMappa {

    public void aggiungiElementiBaseAllaMappa(
            Pane mappaPane,
            ImageView sfondoMappa,
            Label istruzioni,
            Button tornaGioco,
            Button mappaIndietro,
            Button mappaAvanti,
            TextArea logMappa,
            Button combattiMappaButton,
            Button attaccoSquadraMappaButton,
            Button potenziaMappaButton
    ) {
        mappaPane.getChildren().addAll(
                sfondoMappa,
                istruzioni,
                tornaGioco,
                mappaIndietro,
                mappaAvanti,
                logMappa,
                combattiMappaButton,
                attaccoSquadraMappaButton,
                potenziaMappaButton
        );
    }
}