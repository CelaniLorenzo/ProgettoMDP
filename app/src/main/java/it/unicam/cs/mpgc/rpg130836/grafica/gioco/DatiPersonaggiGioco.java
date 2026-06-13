package it.unicam.cs.mpgc.rpg130836.grafica.gioco;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import java.util.List;

public class DatiPersonaggiGioco {

    private final List<Eroe> eroi;
    private final List<Nemico> nemici;

    public DatiPersonaggiGioco(List<Eroe> eroi, List<Nemico> nemici) {
        this.eroi = eroi;
        this.nemici = nemici;
    }

    public List<Eroe> getEroi() {
        return eroi;
    }

    public List<Nemico> getNemici() {
        return nemici;
    }
}