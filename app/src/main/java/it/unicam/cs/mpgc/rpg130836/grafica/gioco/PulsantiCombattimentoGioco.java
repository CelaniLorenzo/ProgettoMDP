package it.unicam.cs.mpgc.rpg130836.grafica.gioco;

import javafx.scene.control.Button;

public class PulsantiCombattimentoGioco {

    private final Button attaccoSquadraButton;
    private final Button potenziaButton;

    /*
     * Costruisce un'istanza di PulsantiCombattimentoGioco.
     */
    public PulsantiCombattimentoGioco(
            Button attaccoSquadraButton,
            Button potenziaButton
    ) {
        this.attaccoSquadraButton = attaccoSquadraButton;
        this.potenziaButton = potenziaButton;
    }

    /*
     * Restituisce il pulsante dell'attacco di squadra.
     */
    public Button getAttaccoSquadraButton() {
        return attaccoSquadraButton;
    }

    /*
     * Restituisce il pulsante di potenziamento.
     */
    public Button getPotenziaButton() {
        return potenziaButton;
    }
}