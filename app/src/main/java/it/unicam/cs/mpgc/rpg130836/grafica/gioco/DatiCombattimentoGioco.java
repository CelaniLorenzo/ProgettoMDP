package it.unicam.cs.mpgc.rpg130836.grafica.gioco;

import it.unicam.cs.mpgc.rpg130836.model.abilita.GestorePotenziamenti;
import it.unicam.cs.mpgc.rpg130836.model.combattimento.Combattimento;

public class DatiCombattimentoGioco {

    private final GestoreCombattimentoGioco gestoreCombattimentoGioco;
    private final GestorePotenziamenti gestorePotenziamentiGioco;
    private final Combattimento combattimento;

    /*
     * Costruisce un'istanza di DatiCombattimentoGioco.
     */
    public DatiCombattimentoGioco(
            GestoreCombattimentoGioco gestoreCombattimentoGioco,
            GestorePotenziamenti gestorePotenziamentiGioco,
            Combattimento combattimento
    ) {
        this.gestoreCombattimentoGioco = gestoreCombattimentoGioco;
        this.gestorePotenziamentiGioco = gestorePotenziamentiGioco;
        this.combattimento = combattimento;
    }

    /*
     * Restituisce il gestore di combattimento gioco.
     */
    public GestoreCombattimentoGioco getGestoreCombattimentoGioco() {
        return gestoreCombattimentoGioco;
    }

    /*
     * Restituisce il gestore di potenziamenti gioco.
     */
    public GestorePotenziamenti getGestorePotenziamentiGioco() {
        return gestorePotenziamentiGioco;
    }

    /*
     * Restituisce il combattimento.
     */
    public Combattimento getCombattimento() {
        return combattimento;
    }
}