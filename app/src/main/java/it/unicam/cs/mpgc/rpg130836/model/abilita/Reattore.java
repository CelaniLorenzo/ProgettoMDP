package it.unicam.cs.mpgc.rpg130836.model.abilita;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;

public class Reattore extends Nemico implements Potenziabile{

    private int livelloEnergia;

    /*
     * Costruisce un'istanza di Reattore.
     */
    public Reattore() {
        super("Reattore", 100, 16, 6);
        this.livelloEnergia = 1;
    }

    /*
     * Applica il potenziamento disponibile.
     */
    @Override
    public void potenzia() {
        livelloEnergia++;
        aumentaAttaccoBase(3);
        aumentaVitaMassimaERecupera(10);
    }

    /*
     * Restituisce la descrizione del potenziamento.
     */
    @Override
    public String descrizionePotenziamento() {
        return "Energia reattore livello " + livelloEnergia;
    }
}