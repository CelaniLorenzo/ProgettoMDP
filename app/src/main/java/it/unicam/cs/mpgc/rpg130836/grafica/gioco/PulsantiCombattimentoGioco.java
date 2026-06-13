package it.unicam.cs.mpgc.rpg130836.grafica.gioco;

import javafx.scene.control.Button;

public class PulsantiCombattimentoGioco {

    private final Button attaccoSquadraButton;
    private final Button potenziaButton;

    public PulsantiCombattimentoGioco(
            Button attaccoSquadraButton,
            Button potenziaButton
    ) {
        this.attaccoSquadraButton = attaccoSquadraButton;
        this.potenziaButton = potenziaButton;
    }

    public Button getAttaccoSquadraButton() {
        return attaccoSquadraButton;
    }

    public Button getPotenziaButton() {
        return potenziaButton;
    }
}