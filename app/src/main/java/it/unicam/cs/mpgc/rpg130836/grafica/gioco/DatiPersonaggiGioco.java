package it.unicam.cs.mpgc.rpg130836.grafica.gioco;

import it.unicam.cs.mpgc.rpg130836.model.personaggi.Eroe;
import it.unicam.cs.mpgc.rpg130836.model.personaggi.Nemico;
import java.util.List;

public class DatiPersonaggiGioco {

    private final List<Eroe> eroi;
    private final List<Nemico> nemici;

    /*
     * Costruisce un'istanza di DatiPersonaggiGioco.
     */
    public DatiPersonaggiGioco(List<Eroe> eroi, List<Nemico> nemici) {
        this.eroi = eroi;
        this.nemici = nemici;
    }

    /*
     * Restituisce gli eroi.
     */
    public List<Eroe> getEroi() {
        return eroi;
    }

    /*
     * Restituisce i nemici.
     */
    public List<Nemico> getNemici() {
        return nemici;
    }
}