package it.unicam.cs.mpgc.rpg130836.grafica.gioco;

import it.unicam.cs.mpgc.rpg130836.model.abilita.GestorePotenziamenti;
import it.unicam.cs.mpgc.rpg130836.model.combattimento.Combattimento;

public class DatiCombattimentoGioco {

    private final GestoreCombattimentoGioco gestoreCombattimentoGioco;
    private final GestorePotenziamenti gestorePotenziamentiGioco;
    private final Combattimento combattimento;

    public DatiCombattimentoGioco(
            GestoreCombattimentoGioco gestoreCombattimentoGioco,
            GestorePotenziamenti gestorePotenziamentiGioco,
            Combattimento combattimento
    ) {
        this.gestoreCombattimentoGioco = gestoreCombattimentoGioco;
        this.gestorePotenziamentiGioco = gestorePotenziamentiGioco;
        this.combattimento = combattimento;
    }

    public GestoreCombattimentoGioco getGestoreCombattimentoGioco() {
        return gestoreCombattimentoGioco;
    }

    public GestorePotenziamenti getGestorePotenziamentiGioco() {
        return gestorePotenziamentiGioco;
    }

    public Combattimento getCombattimento() {
        return combattimento;
    }
}