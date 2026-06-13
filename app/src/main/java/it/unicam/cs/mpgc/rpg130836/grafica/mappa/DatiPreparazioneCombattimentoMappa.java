package it.unicam.cs.mpgc.rpg130836.grafica.mappa;

import it.unicam.cs.mpgc.rpg130836.model.abilita.GestorePotenziamenti;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class DatiPreparazioneCombattimentoMappa {

    private final TextArea logMappa;
    private final GestorePotenziamenti gestorePotenziamentiMappa;
    private final GestoreCombattimentoMappa gestoreCombattimentoMappa;
    private final Button combattiMappaButton;
    private final Button attaccoSquadraMappaButton;
    private final Button potenziaMappaButton;

    public DatiPreparazioneCombattimentoMappa(
            TextArea logMappa,
            GestorePotenziamenti gestorePotenziamentiMappa,
            GestoreCombattimentoMappa gestoreCombattimentoMappa,
            Button combattiMappaButton,
            Button attaccoSquadraMappaButton,
            Button potenziaMappaButton
    ) {
        this.logMappa = logMappa;
        this.gestorePotenziamentiMappa = gestorePotenziamentiMappa;
        this.gestoreCombattimentoMappa = gestoreCombattimentoMappa;
        this.combattiMappaButton = combattiMappaButton;
        this.attaccoSquadraMappaButton = attaccoSquadraMappaButton;
        this.potenziaMappaButton = potenziaMappaButton;
    }

    public TextArea getLogMappa() {
        return logMappa;
    }

    public GestorePotenziamenti getGestorePotenziamentiMappa() {
        return gestorePotenziamentiMappa;
    }

    public GestoreCombattimentoMappa getGestoreCombattimentoMappa() {
        return gestoreCombattimentoMappa;
    }

    public Button getCombattiMappaButton() {
        return combattiMappaButton;
    }

    public Button getAttaccoSquadraMappaButton() {
        return attaccoSquadraMappaButton;
    }

    public Button getPotenziaMappaButton() {
        return potenziaMappaButton;
    }
}