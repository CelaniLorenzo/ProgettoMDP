package it.unicam.cs.mpgc.rpg130836.model.personaggi;

import java.util.Collections;
import java.util.List;

public class GestorePersonaggi {

    private final CreatorePersonaggi creatorePersonaggi;

    /*
     * Costruisce un'istanza di GestorePersonaggi.
     */
    public GestorePersonaggi(CreatorePersonaggi creatorePersonaggi) {
        this.creatorePersonaggi = creatorePersonaggi;
    }

    /*
     * Crea gli eroi.
     */
    public List<Eroe> creaEroi() {
        return creatorePersonaggi.creaEroi();
    }

    /*
     * Crea i nemici mescolati.
     */
    public List<Nemico> creaNemiciMescolati() {
        List<Nemico> nemici = creatorePersonaggi.creaNemici();
        Collections.shuffle(nemici);
        return nemici;
    }

    /*
     * Mescola la lista dei nemici.
     */
    public void mescolaNemici(List<Nemico> nemici) {
        Collections.shuffle(nemici);
    }
}