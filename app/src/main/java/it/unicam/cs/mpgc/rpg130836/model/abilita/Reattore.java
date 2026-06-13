package it.unicam.cs.mpgc.rpg130836.model.abilita;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;

public class Reattore extends Nemico implements Potenziabile{

    private int livelloEnergia;

    public Reattore() {
        super("Reattore", 100, 16, 6);
        this.livelloEnergia = 1;
    }

    @Override
    public void potenzia() {
        livelloEnergia++;
        aumentaAttaccoBase(3);
        aumentaVitaMassimaERecupera(10);
    }

    @Override
    public String descrizionePotenziamento() {
        return "Energia reattore livello " + livelloEnergia;
    }
}