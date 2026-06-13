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

    /*
     * Costruisce un'istanza di DatiPreparazioneCombattimentoMappa.
     */
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

    /*
     * Restituisce il log della mappa.
     */
    public TextArea getLogMappa() {
        return logMappa;
    }

    /*
     * Restituisce il gestore di potenziamenti mappa.
     */
    public GestorePotenziamenti getGestorePotenziamentiMappa() {
        return gestorePotenziamentiMappa;
    }

    /*
     * Restituisce il gestore di combattimento mappa.
     */
    public GestoreCombattimentoMappa getGestoreCombattimentoMappa() {
        return gestoreCombattimentoMappa;
    }

    /*
     * Restituisce il pulsante di combattimento sulla mappa.
     */
    public Button getCombattiMappaButton() {
        return combattiMappaButton;
    }

    /*
     * Restituisce il pulsante dell'attacco di squadra sulla mappa.
     */
    public Button getAttaccoSquadraMappaButton() {
        return attaccoSquadraMappaButton;
    }

    /*
     * Restituisce il pulsante di potenziamento sulla mappa.
     */
    public Button getPotenziaMappaButton() {
        return potenziaMappaButton;
    }
}